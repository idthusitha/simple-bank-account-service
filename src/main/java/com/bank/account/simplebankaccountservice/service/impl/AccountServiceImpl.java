package com.bank.account.simplebankaccountservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bank.account.simplebankaccountservice.document.Account;
import com.bank.account.simplebankaccountservice.document.TransactionHistory;
import com.bank.account.simplebankaccountservice.model.AccountBalanceRequest;
import com.bank.account.simplebankaccountservice.model.AccountBalanceResponse;
import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;
import com.bank.account.simplebankaccountservice.model.AccountDepositRequest;
import com.bank.account.simplebankaccountservice.model.AccountDepositResponse;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalRequest;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalResponse;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateRequest;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateResponse;
import com.bank.account.simplebankaccountservice.service.AccountService;
import com.bank.account.simplebankaccountservice.utilities.CommonUtils;

@Service
public class AccountServiceImpl implements AccountService {

	final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	static final HashMap<Integer, ReentrantLock> locks = new HashMap<Integer, ReentrantLock>();

	@Autowired
	private AccountElasticServiceImpl accountElasticServiceImpl;

	@Override
	public AccountCreateResponse create(AccountCreateRequest accountCreateRequest) {

		Integer accountNumber = accountElasticServiceImpl.generateAccountNumber();

		Account account = new Account();
		account.setAccountNumber(accountNumber);
		BeanUtils.copyProperties(accountCreateRequest, account);
		account.setCreatedDate(CommonUtils.getInstance().getTimeStamp());
		account.setAmount("0.0");
		account.setCurrency(CommonUtils.getInstance().getProperties().getProperty("base.currency.code"));

		accountElasticServiceImpl.save(account);

		AccountCreateResponse accountCreateResponse = new AccountCreateResponse();
		accountCreateResponse.setAccountNumber(account.getAccountNumber());
		accountCreateResponse.setStatus("ACTIVE");

		logger.info("AccountServiceImpl==> create()==>Completed");

		return accountCreateResponse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AccountBalanceResponse balance(AccountBalanceRequest accountBalanceRequest) {
		AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();

		/** Lock the account with account number */
		Lock lock = getLock(accountBalanceRequest.getAccountNumber());

		try {
			lock.lock();

			Account account = new Account();
			account.setAccountNumber(accountBalanceRequest.getAccountNumber());
			account = accountElasticServiceImpl.findByAccountId(account);
			BeanUtils.copyProperties(account, accountBalanceResponse);

			CurrencyExchnageRateResponse currencyExchnageRateResponse = loadCurrencyExchangeRate(accountBalanceRequest, account.getAmount());
			accountBalanceResponse.setCurrency(accountBalanceRequest.getCurrency());
			accountBalanceResponse.setAmount(currencyExchnageRateResponse.getAmount());

			logger.info("AccountServiceImpl==> accountBalanceResponse()==>Completed: " + accountBalanceResponse.getAmount());
		} finally {
			lock.unlock();
		}
		return accountBalanceResponse;
	}

	private CurrencyExchnageRateResponse loadCurrencyExchangeRate(AccountBalanceRequest accountBalanceRequest, String amount) {
		CurrencyExchnageRateResponse currencyExchnageRateResponse = new CurrencyExchnageRateResponse();

		try {

			RestTemplate restTemplate = new RestTemplate();
			String exchangeRateAPI = CommonUtils.getInstance().getProperties().getProperty("exchange.rate.api.url") + "currency/find";
			CurrencyExchnageRateRequest currencyExchnageRateRequest = new CurrencyExchnageRateRequest();
			currencyExchnageRateRequest.setCurrency(accountBalanceRequest.getCurrency());
			currencyExchnageRateRequest.setAmount(amount);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<CurrencyExchnageRateRequest> entity = new HttpEntity<CurrencyExchnageRateRequest>(currencyExchnageRateRequest, headers);

			ResponseEntity<CurrencyExchnageRateResponse> response = restTemplate.exchange(exchangeRateAPI, HttpMethod.POST, entity, CurrencyExchnageRateResponse.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				currencyExchnageRateResponse = response.getBody();
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				// nono... bad credentials
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return currencyExchnageRateResponse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AccountDepositResponse deposit(AccountDepositRequest accountDepositRequest) {
		AccountDepositResponse accountDepositResponse = new AccountDepositResponse();

		/** Lock the account with account number */
		Lock lock = getLock(accountDepositRequest.getAccountNumber());

		try {
			lock.lock();

			Account account = new Account();
			account.setAccountNumber(accountDepositRequest.getAccountNumber());
			account = accountElasticServiceImpl.findByAccountId(account);

			/** Update the transaction history list here */
			List<TransactionHistory> historyList = account.getTransactionHistory();
			TransactionHistory transactionHistory = new TransactionHistory();
			transactionHistory.setDebitAmout(accountDepositRequest.getAmount());
			transactionHistory.setCreditAmout("0.00");
			transactionHistory.setTransactionDate(CommonUtils.getInstance().getTimeStamp());

			historyList = historyList == null ? new ArrayList<TransactionHistory>() : historyList;
			historyList.add(transactionHistory);
			account.setTransactionHistory(historyList);

			Double balance = Double.parseDouble(account.getAmount()) - Double.parseDouble(accountDepositRequest.getAmount());
			account.setAmount("" + balance);

			accountElasticServiceImpl.update(account);
			BeanUtils.copyProperties(account, accountDepositResponse);
			logger.info("AccountServiceImpl==> deposit()==>Completed: " + accountDepositResponse.getAmount());
		} finally {
			lock.unlock();
		}

		return accountDepositResponse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AccountWithdrawalResponse withdrawal(AccountWithdrawalRequest accountWithdrawalRequest) {
		AccountWithdrawalResponse accountWithdrawalResponse = new AccountWithdrawalResponse();
		accountWithdrawalResponse.setStatus("INITIAL-STATE");

		/** Lock the account with account number */
		Lock lock = getLock(accountWithdrawalRequest.getAccountNumber());

		try {
			lock.lock();

			Account account = new Account();
			account.setAccountNumber(accountWithdrawalRequest.getAccountNumber());
			account = accountElasticServiceImpl.findByAccountId(account);

			/** Update the transaction history list here */
			List<TransactionHistory> historyList = account.getTransactionHistory();
			TransactionHistory transactionHistory = new TransactionHistory();
			transactionHistory.setDebitAmout("0.00");
			transactionHistory.setCreditAmout(accountWithdrawalRequest.getAmount());
			transactionHistory.setTransactionDate(CommonUtils.getInstance().getTimeStamp());

			historyList = historyList == null ? new ArrayList<TransactionHistory>() : historyList;
			historyList.add(transactionHistory);
			account.setTransactionHistory(historyList);

			Double balance = Double.parseDouble(account.getAmount()) - Double.parseDouble(accountWithdrawalRequest.getAmount());
			BeanUtils.copyProperties(account, accountWithdrawalResponse);

			if (balance > 0) {
				account.setAmount("" + balance);
				accountElasticServiceImpl.update(account);
				accountWithdrawalResponse.setAmount("" + balance);
				accountWithdrawalResponse.setStatus("SUCCESS");

			} else {
				accountWithdrawalResponse.setStatus("INSUFFICIENT MONEY");
			}
			logger.info("AccountServiceImpl==> deposit()==>withdrawal: " + accountWithdrawalResponse.getAmount());
		} finally {
			lock.unlock();
		}
		return accountWithdrawalResponse;
	}

	private ReentrantLock getLock(Integer id) {
		synchronized (locks) {
			ReentrantLock lock = locks.get(id);
			if (lock == null) {
				lock = new ReentrantLock();
				locks.put(id, lock);
			}
			return lock;
		}
	}
}
