# Java

### Java Webservice with MySQL

* *`Steps`*
  * Create a Java Web Application
  * Add a Java Web Service
  * Add operation for your service
  * To use the name within the SOAP Response
      * Annotate class with @XmlRootElement(name="Student") 
      * Annotate function with @WebResult(name="Student")
  * To define the order of the fields
      * Annotate class with @XmlType(propOrder={"id", "firstname", "lastname"})
* *`For Testing:`* 
  * Right-click Project > Deploy
  * Right-click Service > Test Web Service
