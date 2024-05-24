# Spaceships API

Spaceships API is a CRUD API for managing cinematic spaceships using DDD (Domain-Driven Design) and
hexagonal architecture. This project is built on Spring Boot and provides functionalities for
creating, reading, updating, and deleting spaceships.

## Features

Using the latest stable versions of:

- **Java 17 LTS**: Uses Java version 17.
- **Spring Boot 3.2.5**: Main framework of the project.

### Spring Boot Dependencies:

- **spring-boot-starter-actuator**: Provides monitoring and management capabilities for the
  application.
- **spring-boot-starter-web**: Configures and provides necessary dependencies for building web
  applications.
- **spring-boot-starter-hateoas**: Supports creating RESTful APIs that follow the HATEOAS principle.
- **spring-boot-starter-security**: Adds security to the application (authentication and
  authorization).
- **spring-boot-starter-data-mongodb**: Supports working with MongoDB.

### Developer Tools

- **spring-boot-devtools**: Development tools that enable automatic application reload on code
  changes.

### JAXB Dependencies

- **jakarta.xml.bind-api**: JAXB API for mapping XML data to Java objects and vice versa.
- **jaxb-runtime**: Runtime implementation of JAXB.

### Lombok

- **lombok**: Simplifies code by eliminating the need to write getter, setter, and constructor
  methods.

### Messaging with AMQP

- **spring-boot-starter-amqp**: Supports AMQP-based messaging, including RabbitMQ.

### AOP Support

- **aspectjrt**: AspectJ runtime libraries for Aspect-Oriented Programming support.
- **aspectjweaver**: AspectJ weaver for code instrumentation.

### Reflections for CQRS

- **reflections**: Supports runtime metadata scanning.

### Logging

- **slf4j-api**: API for logging.
- **slf4j-simple**: Simple implementation of SLF4J.

### API Documentation

- **springdoc-openapi-starter-webmvc-ui**: Supports API documentation using OpenAPI.

### Testing Dependencies

- **spring-boot-starter-test**: Necessary dependencies for testing.
- **javafaker**: Test data generator.
- **spring-security-test**: Supports security testing.

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
  # Service for the RabbitMQ
  rabbitmq:
    image: "rabbitmq:3-management"
    ports:
      - "5672:5672"
      - "15672:15672"
    networks:
      - mynetwork

  # Service for MongoDB
  mongo:
    image: mongo
    ports:
      - "27017:27017"
    volumes:
      - ./init-mongo.js:/docker-entrypoint-initdb.d/init-mongo.js:ro
      - mongo_data:/data/db
    networks:
      - mynetwork

  # Service for the API
  spaceships-api:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - rabbitmq
      - mongo
    networks:
      - mynetwork

# Network for the services
networks:
  mynetwork:

volumes:
  mongo_data:
```

Build and start the Docker containers:

```bash
docker-compose up --build
```

### Step 5: Verify the Setup

Once the services are up and running, you can verify that the API is working by accessing the
following endpoints:

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
curl -u Admin:1234 -X PUT -H "Content-Type: application/json" -d '{"name":"New Spaceship Name", "franchise":"New Spaceship Franchise", "maxSpeed":12345}' http://localhost:8080/spaceships/2b1fcfae-757c-463f-9c9f-e5785d29f452
```

### Delete a spaceship

```sh
curl -u Admin:1234 -X DELETE http://localhost:8080/spaceships/2b1fcfae-757c-463f-9c9f-e5785d29f452
```

## Feedback

I have opted to use data mapping from the database to the domain through "Data" classes and a mapper
that performs this function. Another valid and correct option that could avoid the double
transformation we are currently doing (from DB to Data and from Data to Entity) would be to use an
ORM like Hibernate with a custom configuration for direct mapping specified by XML for each entity.
When using Value Objects, I need to specify the type of conversion through the "value()" methods of
each V.O.

I have implemented a single method for managing domain events through RabbitMq (only a publisher has
been implemented). However, I believe the app should have multiple methods for managing domain
events: through a DB (in this case, MongoDb could be used as a PoC backup in case RabbitMq
publication fails) and through RabbitMq (as the primary management method). Additionally, I should
add retry policies and dead letter policies to retry the consumption of these events if necessary.

I have implemented security for the application using Spring Security with basic authentication to
facilitate the PoC. However, it would be better to create more security policies, as well as a
microservice to generate encrypted authenticity tokens, etc.

I have also decided to use a “String” value which I control the generation in my domain, since I
think it is better than using a numeric value for the identifier of each entity. The reason is that
the use of UUIDs is a unique identification and can be generated without collision in a distributed
system.

I have added an @Aspect to write a log line if the UUID does not have a correct UUID format, instead
of writing log if the ID is negative, as requested.

I have created an initial script to enter data into the Mongo database called init-mongo.js which is
located in the root folder. There are the data of 10 test spaceships.

I have used various design patterns (Object Mother, Builder, CQRS, etc.).

If u want give me feedback, please, write me to my mail: svila.smr@gmail.com