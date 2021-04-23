package com.bank.account.simplebankaccountservice.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.bank.account.simplebankaccountservice.BaseIT;
import com.bank.account.simplebankaccountservice.model.AccountBalanceRequest;
import com.bank.account.simplebankaccountservice.model.AccountBalanceResponse;
import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateResponse;
import com.bank.account.simplebankaccountservice.model.AccountDepositRequest;
import com.bank.account.simplebankaccountservice.model.AccountDepositResponse;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalRequest;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalResponse;

public class AccountControllerTest extends BaseIT {

	@Test(groups = "regression")
	public void contextLoads() {
		Assert.assertNotNull(getWebController(), "Controller is null");
	}

	@Test(groups = "regression")
	public void create() throws IOException, ParseException {
		String addURI = "http://localhost:8080/account/create";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");

		logger.info("Add URL :" + addURI);
		AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
		accountCreateRequest.setFirstName("firstName");
		accountCreateRequest.setLastName("firstName");
		accountCreateRequest.setAddress("firstName");
		accountCreateRequest.setCountry("firstName");
		accountCreateRequest.setDateOfBirth("firstName");
		accountCreateRequest.setPersonalNumber(123456789);
		accountCreateRequest.setPhoneNumber(123456789);

		HttpEntity<AccountCreateRequest> entity = new HttpEntity<AccountCreateRequest>(accountCreateRequest, headers);
		ResponseEntity<AccountCreateResponse> response = getRestTemplate().postForEntity(addURI, entity, AccountCreateResponse.class);
		AccountCreateResponse responseObject = response.getBody();

		// Get AccountNumber from the Response object
		System.out.println("AccountNumber is :" + responseObject.getAccountNumber());

		Assert.assertNotNull(responseObject.getAccountNumber());

		// Check if the status code is 201
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		logger.info("Account created successfully:" + responseObject.getAccountNumber());
	}

	@Test(groups = "regression")
	public void deposit() throws IOException, ParseException {
		String addURI = "http://localhost:8080/account/deposit";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");

		logger.info("Add URL :" + addURI);
		AccountDepositRequest accountDepositRequest = new AccountDepositRequest();
		accountDepositRequest.setAccountNumber(100000001);
		accountDepositRequest.setAmount("100.00");

		HttpEntity<AccountDepositRequest> entity = new HttpEntity<AccountDepositRequest>(accountDepositRequest, headers);
		ResponseEntity<AccountDepositResponse> response = getRestTemplate().exchange(addURI, HttpMethod.PUT, entity, AccountDepositResponse.class);
		AccountDepositResponse responseObject = response.getBody();

		// Get AccountNumber from the Response object
		System.out.println("AccountNumber is :" + responseObject.getAccountNumber());

		Assert.assertEquals("" + 100000001, responseObject.getAccountNumber().toString());

		// Check if the status code is 201
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		logger.info("Account created successfully:" + responseObject.getAccountNumber());
	}

	@Test(groups = "regression")
	public void balance() throws IOException, ParseException {
		String addURI = "http://localhost:8080/account/balance";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");

		logger.info("Add URL :" + addURI);
		AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
		accountBalanceRequest.setAccountNumber(100000001);
		accountBalanceRequest.setCurrency("SEK");

		HttpEntity<AccountBalanceRequest> entity = new HttpEntity<AccountBalanceRequest>(accountBalanceRequest, headers);
		ResponseEntity<AccountBalanceResponse> response = getRestTemplate().exchange(addURI, HttpMethod.POST, entity, AccountBalanceResponse.class);
		AccountBalanceResponse responseObject = response.getBody();

		// Get AccountNumber from the Response object
		System.out.println("AccountNumber is :" + responseObject.getAccountNumber());

		Assert.assertEquals("" + 100000001, responseObject.getAccountNumber().toString());
		// Assert.assertEquals(""+100, responseObject.getAmount());

		// Check if the status code is 201
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		logger.info("Account created successfully:" + responseObject.getAccountNumber());
	}

	@Test(groups = "regression")
	public void withdrawal() throws IOException, ParseException {
		String addURI = "http://localhost:8080/account/withdrawal";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");

		logger.info("Add URL :" + addURI);
		AccountWithdrawalRequest accountWithdrawalRequest = new AccountWithdrawalRequest();
		accountWithdrawalRequest.setAccountNumber(100000001);
		accountWithdrawalRequest.setAmount("100.00");

		HttpEntity<AccountWithdrawalRequest> entity = new HttpEntity<AccountWithdrawalRequest>(accountWithdrawalRequest, headers);
		ResponseEntity<AccountWithdrawalResponse> response = getRestTemplate().exchange(addURI, HttpMethod.PUT, entity, AccountWithdrawalResponse.class);
		AccountWithdrawalResponse responseObject = response.getBody();

		// Get AccountNumber from the Response object
		System.out.println("AccountNumber is :" + responseObject.getAccountNumber());

		Assert.assertEquals("" + 100000001, responseObject.getAccountNumber().toString());

		// Check if the status code is 201
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		logger.info("Account created successfully:" + responseObject.getAccountNumber());
	}

	@AfterTest(groups = "regression")
	public void afterTest() {
		logger.info("Clean up after test execution");
		logger.info("Creating RestTemplate object as Null");
		setRestTemplate(new RestTemplate());
	}
}
