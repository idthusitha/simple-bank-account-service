package com.bank.account.simplebankaccountservice.model;

public class AccountCreateResponse {

	private Integer accountNumber;
	private String status;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
