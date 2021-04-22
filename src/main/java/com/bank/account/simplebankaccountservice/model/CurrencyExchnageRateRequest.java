package com.bank.account.simplebankaccountservice.model;

public class CurrencyExchnageRateRequest {

	private String currency;

	private String amount;

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
}
