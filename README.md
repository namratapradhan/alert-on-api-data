# alert-analysis

This is a Spring Boot application that provides a Java web service that analyzes api data and returns results based on some criteria. 

- IDE: Eclipse
- Java version: Java 8
- Maven version: 3.5.0 

Download the project into a folder named 'alert' (or any other name you like)

Using command line, go to the base of the folder and run the following commands:
1. Build the application
>> mvn clean install
2. Run the application
>> java -jar target/alert-0.0.1-SNAPSHOT.jar


To access the web service the end point is http://localhost:8000/alert/date/{date}
Replace {date} with date and time in 24 hour date format "yyyy-MM-dd HH:mm:ss".
For example: http://localhost:8000/alert/date/2018-03-16 15:00:00
    
    







