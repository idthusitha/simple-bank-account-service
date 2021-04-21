package com.bank.account.simplebankaccountservice.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.bank.account.simplebankaccountservice.utilities.StringConstant;

@Document(indexName = StringConstant.PRODUCT_INDEX /*, type = "article"*/)
public class Account {

	@Id
	private Integer accountNumber;

	@Field(type = FieldType.Text)
	private String firstName;

	@Field(type = FieldType.Text)
	private String lastName;

	@Field(type = FieldType.Text)
	private String address;

	@Field(type = FieldType.Text)
	private String country;

	@Field(type = FieldType.Text)
	private String emailAddress;

	@Field(type = FieldType.Date)
	private String dateOfBirth;

	@Field(type = FieldType.Integer)
	private Integer personalNumber;

	@Field(type = FieldType.Double)
	private Double amount;

	@Field(type = FieldType.Integer)
	private Integer phoneNumber;

    @Field(type = FieldType.Nested, includeInParent = true)
	private List<TransactionHistory> transactionHistory;
	
	@Field(type = FieldType.Date)
	private Date createdDate;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(Integer personalNumber) {
		this.personalNumber = personalNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<TransactionHistory> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<TransactionHistory> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = date;
	}

	
}
