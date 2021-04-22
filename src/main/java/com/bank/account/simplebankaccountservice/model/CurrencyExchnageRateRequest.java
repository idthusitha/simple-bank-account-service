package com.bank.account.simplebankaccountservice.model;

public class CurrencyExchnageRateRequest {

	private String currency;

	private Double amount;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
