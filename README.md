# robot-apocalypse
The future of humankind 

To run This Project You will need to have Postgres installed on your machine.
create a database and name it robot

have IntelliJ installed

then create a folder maybe in your Downloads
open the terminal and be in that directory

run these commands "git clone https://github.com/thembelanimkhize29/robot-apocalypse.git"  or you can just Download the whole project
in your application properties ensure that you set the username and password of your Postgres

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/robot  {database name}
spring.datasource.username=postgres         {your username}
spring.datasource.password=12345678        {your password}

having this conf in your application properties
spring.jpa.hibernate.ddl-auto=update  tables will be created automatically, considering you have robot database created

Ensure Zscaler is turned off.


after running the project these are the endpoints you will run
GET
http://localhost:8080/survivors
response status code 200

POST SURVIVOR
http://localhost:8080/survivors
{
  "name": "name",
  "age": 28,
  "gender": "Male",
  "lastLocation": {
    "latitude": 40.7128,
    "longitude": -74.0060
  },
  "inventory": {
    "water": 5,
    "food": 10,
    "medication": 3,
    "ammunition": 20
  }
}

response status code 201(created)

GET BY ID
http://localhost:8080/survivors/1       http://localhost:8080/survivors/{id}
response status code 200

PUT TO REPORT
http://localhost:8080/survivors/1/reportContamination   /http://localhost:8080/survivors/{id}/reportContamination  (send the request three times to alter the status)
response status code 204(NO_CONTENT)

GET INFECTED SURVIVORS
http://localhost:8080/survivors/infectedSurvivors
response status code 200

GET NONINFECTED
http://localhost:8080/survivors/nonInfectedSurvivors
response status code 200

GET THE PERCENTAGE OF INFECTED  
http://localhost:8080/survivors/infectedSurvivorsPercentage
response status code 200

GET THE PERCENTAGE OF NONINFECTED
http://localhost:8080/survivors/nonInfectedSurvivorsPercentage
response status code 200

GET LIST OF ROBOTS
Ensure that Zscaler is turned off to avoid this error (Caused by: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
Zscaler)

http://localhost:8080/api/robotData
response status code 200

Robots have two categories (Flying robots and land robots). 

Land robots
http://localhost:8080/robotData/land
response status code 200

Flying robots
http://localhost:8080/robotData/flying
response status code 200

PUT Location (Update survivor location)

http://localhost:8080/survivors/1  / http://localhost:8080/survivors/{id}
{
"latitude": 33.7833,
"longitude": -132.4167
}
response status code 204(NO_CONTENT)

package com.thembelanimkhize.robotapocalypse.Testcontrollers;
I also have tests, using JUnit 5.













