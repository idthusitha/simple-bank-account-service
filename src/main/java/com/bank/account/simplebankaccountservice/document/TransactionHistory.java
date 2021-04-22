package com.bank.account.simplebankaccountservice.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.bank.account.simplebankaccountservice.utilities.StringConstant;

@Document(indexName = StringConstant.TRANSACTION_HISTORY_INDEX)
public class TransactionHistory {

	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String transactionDate;

	@Field(type = FieldType.Double)
	private String debitAmout;

	@Field(type = FieldType.Double)
	private String creditAmout;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDebitAmout() {
		return debitAmout;
	}

	public void setDebitAmout(String debitAmout) {
		this.debitAmout = debitAmout;
	}

	public String getCreditAmout() {
		return creditAmout;
	}

	public void setCreditAmout(String creditAmout) {
		this.creditAmout = creditAmout;
	}
}
