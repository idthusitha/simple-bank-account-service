package com.bank.account.simplebankaccountservice.service;

import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;

public interface AccountService {

	public AccountCreateResponse create(AccountCreateRequest accountCreateRequest);

}
