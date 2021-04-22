package com.bank.account.simplebankaccountservice.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.bank.account.simplebankaccountservice.utilities.StringConstant;

@Document(indexName = StringConstant.CURRENCY_EXCHANGE_RATE)
public class CurrencyExchangeRate {

	@Id
	private String currency;

	@Field(type = FieldType.Text)
	private String baseCurrencyName;

	@Field(type = FieldType.Text)
	private String baseCurrency;

	@Field(type = FieldType.Text)
	private String currencyName;

	@Field(type = FieldType.Integer)
	private Integer currencyDecimal;

	@Field(type = FieldType.Double)
	private Double exchangeRate;

	@Field(type = FieldType.Text)
	private String updatedDate;

	@Field(type = FieldType.Text)
	private String status;

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getBaseCurrencyName() {
		return baseCurrencyName;
	}

	public void setBaseCurrencyName(String baseCurrencyName) {
		this.baseCurrencyName = baseCurrencyName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Integer getCurrencyDecimal() {
		return currencyDecimal;
	}

	public void setCurrencyDecimal(Integer currencyDecimal) {
		this.currencyDecimal = currencyDecimal;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
