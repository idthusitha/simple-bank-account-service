package com.bank.account.simplebankaccountservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;
import com.bank.account.simplebankaccountservice.service.AccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("account")
public class AccountController {

	final static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@ApiOperation(value = "Create New Account")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(@RequestBody AccountCreateRequest accountCreateRequest,
			HttpServletResponse response) {
		AccountCreateResponse accountCreateResponse = null;
		try {
			logger.info("Starting on method create in AccountController :");

			/** Need to validate the AccountCreateRequest */

			accountCreateResponse = accountService.create(accountCreateRequest);

		} catch (Exception e) {
			logger.error("Error on method create in AccountController :", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Ending on method create in AccountController :");
		if (null == accountCreateResponse) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(accountCreateResponse);
		}

	}
}
