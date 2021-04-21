package com.bank.account.simplebankaccountservice.model;

public class AccountBalanceRequest {

	private Integer accountNumber;
	private String currency;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
