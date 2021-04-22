package com.bank.account.simplebankaccountservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.simplebankaccountservice.document.Account;
import com.bank.account.simplebankaccountservice.document.TransactionHistory;
import com.bank.account.simplebankaccountservice.model.AccountBalanceRequest;
import com.bank.account.simplebankaccountservice.model.AccountBalanceResponse;
import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;
import com.bank.account.simplebankaccountservice.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountElasticServiceImpl accountElasticServiceImpl;

	@Override
	public AccountCreateResponse create(AccountCreateRequest accountCreateRequest) {

		Account account = new Account();
		account.setAccountNumber(123456789);
		BeanUtils.copyProperties(accountCreateRequest, account);
		account.setCreatedDate(new Date());
		account.setAmount(0.0);

		// Set TransactionHistory Data
		List<TransactionHistory> list = new ArrayList<>();
		TransactionHistory transactionHistory = new TransactionHistory();
		list.add(transactionHistory);

		account.setTransactionHistory(list);

		accountElasticServiceImpl.save(account);

		AccountCreateResponse accountCreateResponse = new AccountCreateResponse();
		accountCreateResponse.setAccountNumber(account.getAccountNumber());
		accountCreateResponse.setStatus("ACTIVE");

		return accountCreateResponse;
	}

	@Override
	public AccountBalanceResponse balance(AccountBalanceRequest accountBalanceRequest) {
		AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();

		Account account = new Account();
		BeanUtils.copyProperties(accountBalanceRequest, account);
		account = accountElasticServiceImpl.findByAccountId(account);
		BeanUtils.copyProperties(account, accountBalanceResponse);

		return accountBalanceResponse;
	}

}
