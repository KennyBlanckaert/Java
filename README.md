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
