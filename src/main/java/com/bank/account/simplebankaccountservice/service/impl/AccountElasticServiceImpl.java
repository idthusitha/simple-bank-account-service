package com.bank.account.simplebankaccountservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.bank.account.simplebankaccountservice.document.Account;
import com.bank.account.simplebankaccountservice.document.CurrencyExchangeRate;
import com.bank.account.simplebankaccountservice.utilities.StringConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountElasticServiceImpl {

	final static Logger logger = LoggerFactory.getLogger(AccountElasticServiceImpl.class);

	@Autowired
	ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private ObjectMapper objectMapper;

	public void save(Account account) {

		elasticsearchOperations.indexOps(Account.class).create();
		elasticsearchOperations.save(account);
	}

	public Account findByAccountId(Account account) {
		// query
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("accountNumber", account.getAccountNumber());
		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		SearchHits<Account> productHits = elasticsearchOperations.search(searchQuery, Account.class,
				IndexCoordinates.of(StringConstant.ACCOUNT_INDEX));
		if (productHits.getTotalHits() > 0) {
			account = (productHits.getSearchHits()).get(0).getContent();
		}
		return account;
	}

	public CurrencyExchangeRate findExchangeRate(CurrencyExchangeRate currencyExchangeRate) {
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("currency", currencyExchangeRate.getCurrency());
		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		SearchHits<CurrencyExchangeRate> productHits = elasticsearchOperations.search(searchQuery,
				CurrencyExchangeRate.class, IndexCoordinates.of(StringConstant.CURRENCY_EXCHANGE_RATE));
		if (productHits.getTotalHits() > 0) {
			currencyExchangeRate = (productHits.getSearchHits()).get(0).getContent();
		}
		return currencyExchangeRate;
	}

	public void update(List<CurrencyExchangeRate> list) {
		List<IndexQuery> indexQueries = new ArrayList<>();
		for (CurrencyExchangeRate fooDocument : list) {
			IndexQuery query = new IndexQueryBuilder().withId(String.valueOf(fooDocument.getCurrency()))
					.withObject(fooDocument).build();
			indexQueries.add(query);
		}
		elasticsearchOperations.bulkIndex(indexQueries, IndexCoordinates.of(StringConstant.CURRENCY_EXCHANGE_RATE));
	}

}
