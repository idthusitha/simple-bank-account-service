package com.bank.account.simplebankaccountservice.service;

import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateRequest;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateResponse;

public interface CurrencyExchangeRateService {

	public CurrencyExchnageRateResponse find(CurrencyExchnageRateRequest currencyExchnageRateRequest);

	public void update();

}
