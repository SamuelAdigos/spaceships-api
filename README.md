
# Spaceships API

Spaceships API is a CRUD API for managing cinematic spaceships using DDD (Domain-Driven Design) and hexagonal architecture. This project is built on Spring Boot and provides functionalities for creating, reading, updating, and deleting spaceships.




## Features

Using latest stable versions of:
- Java 17: Uses Java version 17.
- Spring Boot 3.2.5: Main framework of the project.
- H2 Database: In-memory database for development and testing.
- Flyway: Database scripts management.
- Spring Security: Basic authentication security.
- Spring HATEOAS: Adds hypermedia to API responses.
- Spring AMQP: Communication with RabbitMQ.
- SpringDoc OpenAPI: API documentation.


## Installation Guide

### Pre-requisites:
- Docker installed on your machine.
- Java 17 installed.
- Maven installed.

### Step 1: Clone the Repository
First, clone the repository to your local machine:

```bash
  git clone https://github.com/SamuelAdigos/spaceships-api.git
  cd spaceships-api
```

### Step 2: Build the Application
Use Maven to build the application. This will generate the necessary JAR file:

```bash
  mvn clean install
```

### Step 3: Create a Docker Network
Create a Docker network to enable communication between the services:

```bash
  docker network create mynetwork
```

### Step 4: Build and Run Docker Containers
Make sure you have the following docker-compose.yml file in the root of your project:

```
version: '3'
services:
  # Service for the API
  spaceships-api:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - rabbitmq
    networks:
      - mynetwork

  # Service for RabbitMQ
  rabbitmq:
    image: "rabbitmq:3-management"
    ports:
      - "5672:5672"
      - "15672:15672"
    networks:
      - mynetwork

# Network for the services
networks:
  mynetwork:
```

Build and start the Docker containers:

```bash
docker-compose up --build
```

### Step 5: Verify the Setup
Once the services are up and running, you can verify that the API is working by accessing the following endpoints:
- [Swagger API documentation](http://localhost:8080/swagger-ui.html "Swagger API doc")
- [API docs](http://localhost:8080/api-docs "API docs")
- [RabbitMQ Management (default credentials: 'guest'/'guest')](http://localhost:15672/ "RabbitMQ Local Management")



## Usage/Examples

There are two users with different roles:
- NormalUser:1234
- Admin:1234

### Get all spaceships
```sh
curl -u NormalUser:1234 -X GET http://localhost:8080/spaceships
```

### Get paginated spaceships
```sh
curl -u NormalUser:1234 -X GET "http://localhost:8080/spaceships?page=0&size=5&sort=name"
```

### Get spaceship by ID
```sh
curl -u NormalUser:1234 -X GET http://localhost:8080/spaceships/{id}
```

### Get spaceships by name
```sh
curl -u NormalUser:1234 -X GET "http://localhost:8080/spaceships?name={name}"
```


### Create a new spaceship
```sh
curl -u Admin:1234 -X POST -H "Content-Type: application/json" -d '{"name":"Spaceship Name", "franchise":"Spaceship Franchise", "maxSpeed":12}' http://localhost:8080/spaceships
```

### Update an existing spaceship
```sh
curl -u Admin:1234 -X PUT -H "Content-Type: application/json" -d '{"id": 1, "name":"New Spaceship Name", "franchise":"New Spaceship Franchise", "maxSpeed":12345}' http://localhost:8080/spaceships/1
```

### Delete a spaceship
```sh
curl -u Admin:1234 -X DELETE http://localhost:8080/spaceships/{id}
```


## Feedback

I have opted to use data mapping from the database to the domain through "Data" classes and a mapper that performs this function. Another valid and correct option that could avoid the double transformation we are currently doing (from DB to Data and from Data to Entity) would be to use an ORM like Hibernate with a custom configuration for direct mapping specified by XML for each entity. When using Value Objects, I need to specify the type of conversion through the "value()" methods of each V.O.

I have implemented a single method for managing domain events through RabbitMq (only a publisher has been implemented). However, I believe the app should have multiple methods for managing domain events: through a DB (in this case, h2 could be used as a PoC backup in case RabbitMq publication fails) and through RabbitMq (as the primary management method). Additionally, I should add retry policies and dead letter policies to retry the consumption of these events if necessary.

I have implemented security for the application using Spring Security with basic authentication to facilitate the PoC. However, it would be better to create more security policies, as well as a microservice to generate encrypted authenticity tokens, etc.

Although I have used a "Long" value for the numeric ID (to log if it is negative with @Aspect), I believe it would be better to use UUIDs instead of database auto-generated numeric IDs, as they are unique and can be generated without collision in a distributed system.

I have used various design patterns (Command, Object Mother, Builder, etc.).

If u want give me feedback, please, write me to my mail: svila.smr@gmail.com

