package com.bank.account.simplebankaccountservice.document;

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
}
