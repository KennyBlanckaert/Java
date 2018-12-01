# Java

### 1. Java Web Service with MySQL & Java Web Application using PHP mysqli

* *`Steps Web Service`*
  * Create a new Project (here Java EE Web Application)
  * Add a Java Web Service
  * Add operations for your service
  * To use the classname within the SOAP Response
      * Annotate class with @XmlRootElement(name="Student") 
      * Annotate service function with @WebResult(name="Student")
  * To define the order of the fields
      * Annotate class with @XmlType(propOrder={"id", "firstname", "lastname"})
* *`Steps Handlers`*
  * Create a LogicalHandler (for inspecting) or ProtocolHandler (for manipulation) file 
  * Right-click Web Service > Configure handlers > Add Handler class(es)
* *`Steps Web Application`*
  * Add *.html or *.php
  * PHP is used to extract information from the database and to put it into the webpage
  * For larger projects use **Template engine: Twig**
* *`For Testing:`* 
  * Right-click Project > Deploy
  * Right-click Service > Test Web Service
* *`For Use:`*
  * Create a new Project
  * Right-click project > New > Web Service Client > Add WSDL > Finish
  * Drag methods from "Web Service Reference Folder" to use the available operations
  
### 2. Spring Boot persistence, Rest calls & Docker

> Default port is 8080. Can be changed in application.properties with "server.port=..."  
> Solve Cyclic JSON references with *@JsonBackReference* & *@JsonBackReference*

* *`Setting up the database`*
  * Dependencies
    * spring-boot-starter-data-jpa & h2 (MySQL)
    * spring-boot-starter-data-mongodb (Mongo)
  * Entities
    * annotate class with *@Entity* (MySQL) or *@Document* (Mongo)
    * identifiers must be Long (MySQL) or String (Mongo)
    * annotate identifiers with *@Id* & *@GeneratedValue(...)*
  * Repository
    * inferface extending CrudRepository or MongoRepository
    * use *@Query(...)* annotations
  * Bean
      * annotate with *@Bean*
      * add test data here
* *`Communication using REST`*
  * Dependencies
    * spring-boot-starter-web
  * RestController
    * annotate with *@RestController*
    * declare calls with *@RequestMapping(value=..., method=...)*
    * data can be passed using *@Requestparam*, *@PathVariable*, *@RequestBody*, ...
    * refers to service
  * Service
    * annotate with *@Component*
    * refers to repository
* *`Kafka Message Broker`*
  * Dependencies
    * spring-cloud-stream-binder-kafka
  * Producer- & ConsumerChannel interfaces
    * use *@Input(...)* and *@Output(...)* with name of the kafka topic
  * Change "application.properties" file
  * Add *@EnableBindings({...})* in main
  * MessageGateway
    * annotate class with *@MessageGateway*
    * annotate message-method with *@Gateway(requestchannel = ...)*
  * CommandHandler
    * annotate class with *@Component*
    * annotate listener-method with *@StreamListener(ConsumerChannel.\<channelname\>)*
  * API-gateway
    * @Bean RouteLocator gateway(RouteLocatorBuilder builder)
    * builder.routes().route(r -> r.host("*").and().path("/reception/\*\*").uri("..."))
* *`Docker orchestration`*
  * create Dockerfile
  * docker build -t \<name\> .
  * "docker run --name \<name\> -e \<environment_variables\> --volumes "\<source\>:\<target\>" -d \<tag\>" OR docker-compose.yml 
 
> UNFINISHED!
    
### 3. Java Network Game using Sockets

* *`TicTacToeServer`*
    * initializes lobby (BlockingQueue) & starts 2 thread that use the BlockingQueue:
    * one thread uses a ServerSocket for accepting connections (adds players to lobby)
    * one thread takes 2 players from the lobby to create a game
    * the Game class contains the logic. Keeps waiting for (valid) moves & fills playerboard
    * communicates with the Player class that communicates with the client using a Socket (BufferedReader + PrintWriter)
 * *`TicTacToeClient`*
    * intializes a socket for communication (BufferedReader + PrintWriter)
    * updates the boardFrame using an Observer/Listener
    * stays in an endless loop parsing and sending commands until the players want to quit

### 4. Java JMS 

* *`Add Steps`*
