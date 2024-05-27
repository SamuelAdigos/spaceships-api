package com.samuel.spaceships.api;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;

@Configuration
public class RabbitMqTestContainerConfig {

  @Container
  public static RabbitMQContainer rabbitMqContainer = new RabbitMQContainer("rabbitmq:latest")
      .withExposedPorts(5672);

  static {
    rabbitMqContainer.start();
    var mappedPort = rabbitMqContainer.getMappedPort(5672);
    System.setProperty("rabbitmq.container.port", String.valueOf(mappedPort));
  }
}
