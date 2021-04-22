package com.bank.account.simplebankaccountservice.utilities;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bank.account.simplebankaccountservice.document.CurrencyExchangeRate;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateRequest;
import com.bank.account.simplebankaccountservice.model.CurrencyExchnageRateResponse;

/**
 * 
 * @author Thusitha Dissanayaka
 * 
 */
public class CurrencyExchangeRateUtils {
	final static Logger logger = LoggerFactory.getLogger(CurrencyExchangeRateUtils.class);

	private CurrencyExchangeRateUtils() {
	}

	public static Properties properties = null;
	private static CurrencyExchangeRateUtils instance = null;

	/**
	 * @return CurrencyExchangeRateUtils
	 */
	public static CurrencyExchangeRateUtils getInstance() {
		if (instance == null) {
			instance = new CurrencyExchangeRateUtils();
		}
		return instance;
	}

	public List<CurrencyExchangeRate> loadDeafaultCurrencyExchangeRate() {
		List<CurrencyExchangeRate> list = new ArrayList();
		try {
			String baseCurrency = CommonUtils.getInstance().getProperties().getProperty("base.currency.code");
			String baseCurrencyName = CommonUtils.getInstance().getProperties().getProperty("base.currency.name");

			CurrencyExchangeRate currency = new CurrencyExchangeRate();
			currency.setBaseCurrency(baseCurrency);
			currency.setBaseCurrencyName(baseCurrencyName);
			currency.setCurrency("SEK");
			currency.setCurrencyName("Swedish Kron");
			currency.setCurrencyDecimal(2);
			currency.setExchangeRate(1.0);
			currency.setUpdatedDate(new Date());
			list.add(currency);

			currency = new CurrencyExchangeRate();
			currency.setBaseCurrency(baseCurrency);
			currency.setBaseCurrencyName(baseCurrencyName);
			currency.setCurrency("USD");
			currency.setCurrencyName("US dollars");
			currency.setCurrencyDecimal(2);
			currency.setExchangeRate(1 / 8.44);
			currency.setUpdatedDate(new Date());
			list.add(currency);

			currency = new CurrencyExchangeRate();
			currency.setBaseCurrency(baseCurrency);
			currency.setBaseCurrencyName(baseCurrencyName);
			currency.setCurrency("EUR");
			currency.setCurrencyName("Euro");
			currency.setCurrencyDecimal(2);
			currency.setExchangeRate(1 / 10.11);
			currency.setUpdatedDate(new Date());
			list.add(currency);

			currency = new CurrencyExchangeRate();
			currency.setBaseCurrency(baseCurrency);
			currency.setBaseCurrencyName(baseCurrencyName);
			currency.setCurrency("PHP");
			currency.setCurrencyName("pesos");
			currency.setCurrencyDecimal(2);
			currency.setExchangeRate(1 / 0.17);
			currency.setUpdatedDate(new Date());
			list.add(currency);

			currency = new CurrencyExchangeRate();
			currency.setBaseCurrency(baseCurrency);
			currency.setBaseCurrencyName(baseCurrencyName);
			currency.setCurrency("GBP");
			currency.setCurrencyName("British pound sterling");
			currency.setCurrencyDecimal(2);
			currency.setExchangeRate(1 / 11.69);
			currency.setUpdatedDate(new Date());
			list.add(currency);

			currency = new CurrencyExchangeRate();
			currency.setBaseCurrency(baseCurrency);
			currency.setBaseCurrencyName(baseCurrencyName);
			currency.setCurrency("INR");
			currency.setCurrencyName("Indian rupee");
			currency.setCurrencyDecimal(2);
			currency.setExchangeRate(1 / 0.11);
			currency.setUpdatedDate(new Date());
			list.add(currency);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getAmmount(Double amount, CurrencyExchnageRateResponse currencyExchnageRateResponse) {

		amount = amount / currencyExchnageRateResponse.getExchangeRate();
		return decimalDigits(currencyExchnageRateResponse.getCurrencyDecimal(), amount);
	}

	public String decimalDigits(int decimaldigits, double x) {
		final NumberFormat numFormat = NumberFormat.getNumberInstance();
		numFormat.setMaximumFractionDigits(decimaldigits);

		final String resultS = numFormat.format(x);
		String parsable = resultS.replace(".", "");
		parsable = resultS.replace(",", ".");
		return parsable;
	}
}
