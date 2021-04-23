package com.bank.account.simplebankaccountservice.configuration;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${version.number}")
	private String version;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)				
				.select()
				.apis(RequestHandlerSelectors.any())
				
				.paths(paths())
				.build()
				.apiInfo(apiInfo())
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class);
	}	
	 
	
	private Predicate<String> paths() {
		return and(or( // Paths to Load
				regex("/account.*"),
				regex("/currency.*")));
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
				.title("simple bank account service API").description("simple bank account service API Documentation")
	            .version(version)
	            .build();
	}

}
