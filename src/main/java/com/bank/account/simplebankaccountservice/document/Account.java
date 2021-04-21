package com.bank.account.simplebankaccountservice.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.bank.account.simplebankaccountservice.utilities.StringConstant;

@Document(indexName = StringConstant.PRODUCT_INDEX)
public class Account {

	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String project_id;

	@Field(type = FieldType.Text)
	private String week;

	@Field(type = FieldType.Text)
	private String issue_id;

	@Field(type = FieldType.Text)
	private String type;

	@Field(type = FieldType.Text)
	private String current_state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrent_state() {
		return current_state;
	}

	public void setCurrent_state(String current_state) {
		this.current_state = current_state;
	}
}
