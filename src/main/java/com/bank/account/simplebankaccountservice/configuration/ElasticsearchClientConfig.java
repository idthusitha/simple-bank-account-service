package com.bank.account.simplebankaccountservice.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.bank.account.simplebankaccountservice")
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

	final static Logger logger = LoggerFactory.getLogger(ElasticsearchClientConfig.class);

	@Value("${elasticsearch.host}")
	private String elasticsearchHost;

	@Value("${elasticsearch.port}")
	private String elasticsearchPort;

	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {

		logger.info("elasticsearch.host : " + elasticsearchHost);
		logger.info("elasticsearch.port : " + elasticsearchPort);

		final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
				.connectedTo(elasticsearchHost + ":" + elasticsearchPort).build();

		return RestClients.create(clientConfiguration).rest();
	}
}
