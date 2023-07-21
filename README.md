# Credit_Application_System

It is a restful credit application system that receives credit application requests and returns the credit result to the customer according to the relevant criteria. It is written using the Spring Boot framework. For this credit application system project, which was built using monolithic architecture. The project has been tried to be done in accordance with Solid principles. 

Almost all of the unit and integration tests run successfully. Also swagger has been added and exceptions are handled as much as possible. The project was made in accordance with the Rest conventions. Postgresql and JPA/Hibernate was used.

In addition, when creating a customer, the customer's credit score is generated randomly.

Architecture Used In The Project
While developing this project, I preferred the monolithic architecture because it would be easier and faster to manage and develop due to the small size of the project, and for easy testing and error tracking.
At the same time, monolithic architecture is preferred because it will have a single jar file for deployment.
The project consists of layers such as model, repository, service, controller using layered architecture.


While developing this project, I preferred the monolithic architecture because it would be easier and faster to manage and develop due to the small size of the project, and for easy testing and error tracking.
At the same time, monolithic architecture is preferred because it will have a single jar file for deployment.
The project consists of layers such as model, repository, service, controller using layered architecture.

## Requirements
For Building and running the application belows are required:

Spring Boot 
JDK 
Maven 
PostgreSQL
Swagger 
Lombok
JUnit
## Functional Requirements
New customers can be created in the system, existing customers can be updated or deleted.
If the credit score is below 500, the customer will be rejected. (Credit result: Rejected)
If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the credit application of the customer is approved and a limit of 10.000 TL is assigned to the customer. (Credit Result: Aproved)
If the credit score is between 500 points and 1000 points and the monthly income is above 5000 TL, the credit application of the customer is approved and a 20.000 TL limit is assigned to the customer. (Credit Result: Approved)
If the credit score is equal to or above 1000 points, the customer is assigned a limit equal to MONTHLY INCOME * CREDIT LIMIT MULTIPLIER.The credit limit multiplier is 4 by default. (Credit Result: Approved)
As a result of the conclusion of the credit, the relevant application is recorded in the database. 

## Security
Endpoints are secured using Spring Web Security and Json web token.But all endpoints are permitted in web security config to be used from swagger.

## Database Relation
![DbRelation](https://user-images.githubusercontent.com/97551928/184558983-e93ecaf0-7085-4b1e-923f-4769096751c2.PNG)
## Use-Case 
![Use-Case](https://user-images.githubusercontent.com/97551928/184559896-8a834c14-2165-4d61-9b7b-09fc52f851d7.PNG)
## Swagger
http://localhost:8090/swagger-ui/index.html#/
