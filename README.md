# Simple Bank Account Service API

### Consideration

   * test

### Deriving the solution

   * test

### Key Decisions

   * test

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
   
### Start Elasticsearch  
	#pull elastic search docker image(only once)
	docker pull docker.elastic.co/elasticsearch/elasticsearch:7.11.2
	
	#run elastic search on docker
	docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.11.2
	
### Build Bank Account Application :

	#Go to the project home directory which is simple-bank-account-service
	cd /[project-home-directory]
	./gradlew clean build


### Run Bank Account Application :

	#Go to the project home directory which is simple-bank-account-service
	cd /[project-home-directory]
	./gradlew bootRun

### Application Swagger API
	
	http://localhost:8080/swagger-ui.html#