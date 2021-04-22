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

import com.bank.account.simplebankaccountservice.model.AccountBalanceRequest;
import com.bank.account.simplebankaccountservice.model.AccountBalanceResponse;
import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;
import com.bank.account.simplebankaccountservice.model.AccountDepositRequest;
import com.bank.account.simplebankaccountservice.model.AccountDepositResponse;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalRequest;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalResponse;
import com.bank.account.simplebankaccountservice.service.AccountService;
import com.bank.account.simplebankaccountservice.utilities.ValidatorLogic;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("account")
public class AccountController {

	final static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@ApiOperation(value = "Create New Account")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> create(@RequestBody AccountCreateRequest accountCreateRequest, HttpServletResponse response) {
		AccountCreateResponse accountCreateResponse = null;
		try {
			logger.info("Starting on method create in AccountController :");

			//Request Validation
			if(ValidatorLogic.validationAccountCreateRequest(accountCreateRequest)) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

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

	

	@ApiOperation(value = "Check Account Balance")
	@RequestMapping(value = "/balance", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> balance(@RequestBody AccountBalanceRequest accountBalanceRequest, HttpServletResponse response) {
		AccountBalanceResponse accountBalanceResponse = null;
		try {
			logger.info("Starting on method balance in AccountController :");

			//Request Validation
			if(ValidatorLogic.validationAccountBalanceRequest(accountBalanceRequest)) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

			accountBalanceResponse = accountService.balance(accountBalanceRequest);

		} catch (Exception e) {
			logger.error("Error on method balance in AccountController :", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Ending on method balance in AccountController :");
		if (null == accountBalanceResponse) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(accountBalanceResponse);
		}

	}

	

	@ApiOperation(value = "Deposit Account")
	@RequestMapping(value = "/deposit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> deposit(@RequestBody AccountDepositRequest accountDepositRequest, HttpServletResponse response) {
		AccountDepositResponse accountDepositResponse = null;
		try {
			logger.info("Starting on method deposit in AccountController :");

			//Request Validation
			if(ValidatorLogic.validationAccountDepositRequest(accountDepositRequest)) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

			accountDepositResponse = accountService.deposit(accountDepositRequest);
			accountDepositResponse.setStatus("SUCCESS");

		} catch (Exception e) {
			logger.error("Error on method deposit in AccountController :", e);
			accountDepositResponse.setStatus("FAILED");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Ending on method deposit in AccountController :");
		if (null == accountDepositResponse) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(accountDepositResponse);
		}
	}
	
	@ApiOperation(value = "Withdrawal Account")
	@RequestMapping(value = "/withdrawal", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> withdrawal(@RequestBody AccountWithdrawalRequest accountWithdrawalRequest, HttpServletResponse response) {
		AccountWithdrawalResponse accountWithdrawalResponse = null;
		try {
			logger.info("Starting on method withdrawal in AccountController :");

			//Request Validation
			if(ValidatorLogic.validationAccountWithdrawalRequest(accountWithdrawalRequest)) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

			accountWithdrawalResponse = accountService.withdrawal(accountWithdrawalRequest);			

		} catch (Exception e) {
			logger.error("Error on method withdrawal in AccountController :", e);
			accountWithdrawalResponse.setStatus("FAILED");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Ending on method withdrawal in AccountController :");
		if (null == accountWithdrawalResponse) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(accountWithdrawalResponse);
		}
	}
}
