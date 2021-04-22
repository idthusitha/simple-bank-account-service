package com.bank.account.simplebankaccountservice.service;

import com.bank.account.simplebankaccountservice.model.AccountBalanceRequest;
import com.bank.account.simplebankaccountservice.model.AccountBalanceResponse;
import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;
import com.bank.account.simplebankaccountservice.model.AccountDepositRequest;
import com.bank.account.simplebankaccountservice.model.AccountDepositResponse;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalRequest;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalResponse;

public interface AccountService {

	public AccountCreateResponse create(AccountCreateRequest accountCreateRequest);

	public AccountBalanceResponse balance(AccountBalanceRequest accountBalanceRequest);

	public AccountDepositResponse deposit(AccountDepositRequest accountDepositRequest);

	public AccountWithdrawalResponse withdrawal(AccountWithdrawalRequest accountWithdrawalRequest);

}
