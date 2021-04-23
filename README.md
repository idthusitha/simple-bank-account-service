# Simple Bank Account Service API

### OBJECTIVE

   * Build a simple self-contained bank account REST service. While being a simple fictive assignment feel
free to add other aspects to make it more production ready.

### FUNCTIONAL REQUIREMENTS

   * Support create account   	   	
   * Support deposit and withdrawal to account.
   * Support show account balance in currency of choice. Currency of the account (used in deposit
		and withdrawal) is SEK.
   * Currency exchange rates should be static but changing to fetch it from an external REST
	service should be fairly easy.
   * Negative account balance is not allowed.

### TECHNICAL REQUIREMENTS

   * Account number of 9 digits.
   * Use Java 11 (or later).
   * Use Spring Boot 2.x.
   * Allow concurrent operations.
   * Use in memory data structures or in memory database.

### Create Bank Account JSON format 

   * test
	
    {
       "firstName" : "test user"
      
    }
    
### Data Processing

   * test
  
### Bank Account System Context



### Bank Account System Containers



### Bank Account System (API Application) Components



### Prerequisites to run Bank Account System :
   * git
   * Java 11
   * Docker 20
   * Docker-compose 20
   * elasticsearch 7.11.2
   
   
### Build Bank Account Application :

	cd {{ WORKSPACE_PATH }}
	git clone https://github.com/idthusitha/simple-bank-account-service.git
	cd  simple-bank-account-service
	./gradlew clean build
		
	#Docker image build
	./gradlew clean buildDocker
	

### Run Bank Account Application :

	#Go to the project home directory which is simple-bank-account-service
	cd /[project-home-directory]
	./gradlew bootRun
		
	./gradlew clean build bootRun --debug-jvm
	 Listening for transport dt_socket at address: 5005
	 
	 
	 #Start Elastic Search
	 cd /[project-home-directory]/docker/elasticsearch
	 docker-compose up
	 
	 #Start the application
	 cd /[project-home-directory]/docker/application
	 docker-compose up

### Application Swagger API

![Test Image 1](https://github.com/idthusitha/simple-bank-account-service/blob/master/doc/swagger_api.png)
	
	http://localhost:8080/swagger-ui.html#


