package com.samuel.spaceships.api.Infrastructure.Bus.Event.RabbitMq;

import com.samuel.spaceships.api.Application.Create.SpaceshipCreatedEventPublisher;
import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitSpaceshipCreatedEventPublisher implements SpaceshipCreatedEventPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final Logger logger = LoggerFactory.getLogger(RabbitSpaceshipCreatedEventPublisher.class);

  public RabbitSpaceshipCreatedEventPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void publishSpaceshipCreatedEvent(SpaceshipCreatedDomainEvent event) {
    logger.info("Publishing event: {}", event.toString());
    rabbitTemplate.convertAndSend("spaceship_created_queue", event);
  }
}