package com.bank.account.simplebankaccountservice.model;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class CurrencyExchnageRateResponse {

	private String currency;

	private String amount;

	private String baseCurrency;

	private String baseAmount;

	private Integer currencyDecimal;

	private Double exchangeRate;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(String baseAmount) {
		this.baseAmount = baseAmount;
	}

	public Integer getCurrencyDecimal() {
		return currencyDecimal;
	}

	public void setCurrencyDecimal(Integer currencyDecimal) {
		this.currencyDecimal = currencyDecimal;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
}
