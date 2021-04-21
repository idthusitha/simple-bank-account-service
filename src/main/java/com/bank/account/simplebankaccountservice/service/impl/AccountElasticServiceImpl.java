package com.bank.account.simplebankaccountservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class AccountElasticServiceImpl {

	final static Logger logger = LoggerFactory.getLogger(AccountElasticServiceImpl.class);

	@Autowired
	ElasticsearchOperations elasticsearchOperations;

}
