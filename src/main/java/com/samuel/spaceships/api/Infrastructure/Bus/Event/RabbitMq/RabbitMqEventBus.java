package com.samuel.spaceships.api.Infrastructure.Bus.Event.RabbitMq;

import com.samuel.spaceships.api.Application.Create.SpaceshipCreatedEventPublisher;
import com.samuel.spaceships.api.Domain.DomainEvent;
import com.samuel.spaceships.api.Domain.EventBus;
import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RabbitMqEventBus implements EventBus {

  private final SpaceshipCreatedEventPublisher eventPublisher;

  @Override
  public void publish(List<DomainEvent> events) {
    for (Object event : events) {
      if (event instanceof SpaceshipCreatedDomainEvent) {
        eventPublisher.publishSpaceshipCreatedEvent((SpaceshipCreatedDomainEvent) event);
      }
    }
  }
}
