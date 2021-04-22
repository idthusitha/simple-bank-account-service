package com.bank.account.simplebankaccountservice.model;

public class AccountDepositRequest {

	private Integer accountNumber;
	private String amount;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
