package com.bank.account.simplebankaccountservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.bank.account.simplebankaccountservice.document.Account;
import com.bank.account.simplebankaccountservice.document.AccountNumber;
import com.bank.account.simplebankaccountservice.document.CurrencyExchangeRate;
import com.bank.account.simplebankaccountservice.utilities.StringConstant;

@Service
public class AccountElasticServiceImpl {

	final static Logger logger = LoggerFactory.getLogger(AccountElasticServiceImpl.class);
	final static Integer DEFAULT_ACCOUNT_NUMBER = 1000000000; /**9 digit account number*/
	@Autowired
	ElasticsearchOperations elasticsearchOperations;

	public void save(Account account) {
		elasticsearchOperations.save(account);
	}

	public Account findByAccountId(Account account) {
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("accountNumber", account.getAccountNumber());
		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		SearchHits<Account> productHits = elasticsearchOperations.search(searchQuery, Account.class, IndexCoordinates.of(StringConstant.ACCOUNT_INDEX));
		if (productHits.getTotalHits() > 0) {
			account = (productHits.getSearchHits()).get(0).getContent();
		}
		return account;
	}

	public CurrencyExchangeRate findExchangeRate(CurrencyExchangeRate currencyExchangeRate) {
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("currency", currencyExchangeRate.getCurrency());
		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		SearchHits<CurrencyExchangeRate> productHits = elasticsearchOperations.search(searchQuery, CurrencyExchangeRate.class, IndexCoordinates.of(StringConstant.CURRENCY_EXCHANGE_RATE));
		if (productHits.getTotalHits() > 0) {
			currencyExchangeRate = (productHits.getSearchHits()).get(0).getContent();
		}
		return currencyExchangeRate;
	}

	public void update(List<CurrencyExchangeRate> list) {
		List<IndexQuery> indexQueries = new ArrayList<>();
		for (CurrencyExchangeRate fooDocument : list) {
			IndexQuery query = new IndexQueryBuilder().withId(String.valueOf(fooDocument.getCurrency())).withObject(fooDocument).build();
			indexQueries.add(query);
		}
		elasticsearchOperations.bulkIndex(indexQueries, IndexCoordinates.of(StringConstant.CURRENCY_EXCHANGE_RATE));
	}

	public void update(Account account) {
		elasticsearchOperations.save(account);
	}

	public Integer generateAccountNumber() {
		AccountNumber accountNumber = new AccountNumber();
		accountNumber.setAccountNumber(DEFAULT_ACCOUNT_NUMBER);
		Long count = 0L;
		try {
			QueryBuilder queryBuilder = QueryBuilders.matchQuery("accountNumber", accountNumber.getAccountNumber());
			Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

			count = elasticsearchOperations.count(searchQuery, AccountNumber.class, IndexCoordinates.of(StringConstant.ACCOUNT_NUMBER_INDEX));

			accountNumber.setAccountNumber(DEFAULT_ACCOUNT_NUMBER + count.intValue() + 1);
			elasticsearchOperations.save(accountNumber);

		} catch (NoSuchIndexException e) {
			elasticsearchOperations.save(accountNumber);
			accountNumber.setAccountNumber(DEFAULT_ACCOUNT_NUMBER + count.intValue() + 1);
		}
		return accountNumber.getAccountNumber();
	}

}
