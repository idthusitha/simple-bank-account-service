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

import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateRequest;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateResponse;
import com.bank.account.simplebankaccountservice.service.CurrencyExchangeRateService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("currency")
public class ExchangeRateController {

	final static Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);

	@Autowired
	private CurrencyExchangeRateService currencyExchangeRateService;

	@ApiOperation(value = "find Exchange Rate")
	@RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> find(@RequestBody CurrencyExchnageRateRequest currencyExchnageRateRequest,
			HttpServletResponse response) {
		CurrencyExchnageRateResponse currencyExchnageRateResponse = null;
		try {
			logger.info("Starting on method find in ExchangeRateController :");

			/** Need to validate the AccountCreateRequest */

			currencyExchnageRateResponse = currencyExchangeRateService.find(currencyExchnageRateRequest);

		} catch (Exception e) {
			logger.error("Error on method find in ExchangeRateController :", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Ending on method find in ExchangeRateController :");
		if (null == currencyExchnageRateResponse) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(currencyExchnageRateResponse);
		}

	}

	@ApiOperation(value = "update Currency Exchange Rate")
	@RequestMapping(value = "/update", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> update(HttpServletResponse response) {
		try {
			logger.info("Starting on method update in ExchangeRateController :");

			currencyExchangeRateService.update();

		} catch (Exception e) {
			logger.error("Error on method update in ExchangeRateController :", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Ending on method update in ExchangeRateController :");
		return ResponseEntity.ok("{\"status\": \"SUCCESS\"}");
	}
}
