package com.bank.account.simplebankaccountservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;
import com.bank.account.simplebankaccountservice.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountElasticServiceImpl accountElasticServiceImpl;

	@Override
	public AccountCreateResponse create(AccountCreateRequest accountCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
