# Spring Boot Project - Customer-MS

This is a sample Spring Boot project that demonstrates the use of some common features of Spring Boot.

## Getting Started

### Prerequisites

- Java 11 or later
- Maven 3.8.3 or later

### Building the Project

To build the project, run the following command in the root directory:

mvn clean install

### Running the Project

To run the project, run the following command in the root directory:

mvn spring-boot:run

This will start the application on port 8080.

### Testing the Project

To test the project, run the following command in the root directory:

mvn test


This will run all the unit tests in the project.

## Features

This project includes the following features:

- A RESTful API that supports CRUD operations on a Customer resource
- Integration with an in-memory H2 database for storing the sample resource data

### APIs: 
1. Create Customer
   - POST /api/v1/customers
   - Request: Customer application/json
   - Response: 
     - Success: 201 Created
     - Error: 400 BadRequestException for invalid input 
2. Get All Customers
   - GET /api/v1/customers
   - Response:
     - Success: 200 OK, returns all existing customers from DB, empty list in case no customer present 
3. Get Customer By Id
   - GET /api/v1/customers/{customerId}
   - Response:
     - Success: 200 OK, Returns Customer
     - Error: 404 Not found, if customer not found 
     - Error: 400 Bad Request, if invalid customerId provided in input
4. Update Customer By Id
    - PUT /api/v1/customers/{customerId}
    - Request: Customer application/json
    - Response:
        - Success: 200 OK, Returns updated Customer
        - Error: 404 Not found, if customer not found
        - Error: 400 Bad Request, if Customer or customerId provided in invalid
5. Delete Customer By Id
    - DELETE /api/v1/customers/{customerId}
    - Response:
        - Success: 204 No Content
        - Error: 404 Not found, if customer not found
        - Error: 400 Bad Request, if Customer or customerId provided in invalid
