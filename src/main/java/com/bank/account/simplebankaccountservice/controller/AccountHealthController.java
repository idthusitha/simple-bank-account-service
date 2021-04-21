package com.bank.account.simplebankaccountservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("account/health")
public class AccountHealthController {

	final static Logger logger = LoggerFactory.getLogger(AccountHealthController.class);

	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> status() {
		logger.debug("Enters on method status in IssuesSummaryHealthController : ");
		return ResponseEntity.ok("{ \"application\":\"simple-bank-account-service\",\"status\":\"success\"} ");
	}

}
