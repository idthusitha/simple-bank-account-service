package com.bank.account.simplebankaccountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.bank.account.simplebankaccountservice.controller.WebController;

@AutoConfigureMockMvc
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = SimpleBankAccountServiceApplication.class)
public class BaseIT extends AbstractTestNGSpringContextTests {

	/***
	 * Common methods, variables and constants
	*/

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeClass(groups = "regression")
	public void setupMock() {
		logger.info("Setting up mockMvc");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private RestTemplate restTemplate;

	@BeforeTest(groups = "regression")
	public void setup() {
		logger.info("Setting up restTemplate");
		this.restTemplate = new RestTemplate();
	}

	@Autowired
	private WebController controller;

	public WebController getWebController() {
		return controller;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
