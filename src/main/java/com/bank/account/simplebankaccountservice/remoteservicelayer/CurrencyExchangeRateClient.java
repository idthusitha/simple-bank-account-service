package com.bank.account.simplebankaccountservice.remoteservicelayer;

import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateRequest;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateResponse;
import com.bank.account.simplebankaccountservice.utilities.CommonUtils;

@Service
public class CurrencyExchangeRateClient {
	
	final static Logger logger = LoggerFactory.getLogger(CurrencyExchangeRateClient.class);

	public CurrencyExchnageRateResponse getExchangeRate(String curency, String amount) {

		CurrencyExchnageRateResponse currencyExchnageRateResponse = null;
		Properties prop = CommonUtils.getInstance().getProperties();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String exchangeRateAPI = prop.getProperty("exchange.rate.api.url") + "currency/find";
			CurrencyExchnageRateRequest currencyExchnageRateRequest = new CurrencyExchnageRateRequest();
			currencyExchnageRateRequest.setCurrency(curency);
			currencyExchnageRateRequest.setAmount(amount);

			String authKey = prop.getProperty("currrency.exchange.rate.user.name") + ":" + prop.getProperty("currrency.exchange.rate.password");
			Base64 base64 = new Base64();
			String encoding = base64.encodeToString(authKey.getBytes());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Basic " + encoding);

			HttpEntity<CurrencyExchnageRateRequest> entity = new HttpEntity<CurrencyExchnageRateRequest>(currencyExchnageRateRequest, headers);

			ResponseEntity<CurrencyExchnageRateResponse> response = restTemplate.exchange(exchangeRateAPI, HttpMethod.POST, entity, CurrencyExchnageRateResponse.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				currencyExchnageRateResponse = response.getBody();
			} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				// nono... bad credentials
			}
		} catch (Exception e) {
			logger.error("Error==> ",e);
		}

		return currencyExchnageRateResponse;
	}

}
