package com.bank.account.simplebankaccountservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.simplebankaccountservice.document.CurrencyExchangeRate;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateRequest;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateResponse;
import com.bank.account.simplebankaccountservice.service.CurrencyExchangeRateService;
import com.bank.account.simplebankaccountservice.utilities.CommonUtils;
import com.bank.account.simplebankaccountservice.utilities.CurrencyExchangeRateUtils;

@Service
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {

	final static Logger logger = LoggerFactory.getLogger(CurrencyExchangeRateServiceImpl.class);

	@Autowired
	private AccountElasticServiceImpl accountElasticServiceImpl;

	@Override
	public CurrencyExchnageRateResponse find(CurrencyExchnageRateRequest currencyExchnageRateRequest) {
		CurrencyExchnageRateResponse currencyExchnageRateResponse = new CurrencyExchnageRateResponse();

		CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
		currencyExchangeRate.setCurrency(currencyExchnageRateRequest.getCurrency());

		currencyExchangeRate = accountElasticServiceImpl.findExchangeRate(currencyExchangeRate);
		BeanUtils.copyProperties(currencyExchangeRate, currencyExchnageRateResponse);

		currencyExchnageRateResponse.setBaseAmount(CurrencyExchangeRateUtils.getInstance().getAmmount(currencyExchnageRateRequest.getAmount(), currencyExchnageRateResponse));
		currencyExchnageRateResponse.setCurrency(currencyExchangeRate.getCurrency());

		String baseDecimalPlace = CommonUtils.getInstance().getProperties().getProperty("base.currency.decimalplace");
		Double amount = Double.parseDouble(currencyExchnageRateRequest.getAmount());
		currencyExchnageRateResponse.setAmount(CurrencyExchangeRateUtils.getInstance().decimalDigits(Integer.parseInt(baseDecimalPlace), amount));
		currencyExchnageRateResponse.setBaseCurrency(currencyExchangeRate.getBaseCurrency());

		return currencyExchnageRateResponse;
	}

	@Override
	public void update() {
		List<CurrencyExchangeRate> list = CurrencyExchangeRateUtils.getInstance().loadDeafaultCurrencyExchangeRate();

		accountElasticServiceImpl.update(list);
	}

}
