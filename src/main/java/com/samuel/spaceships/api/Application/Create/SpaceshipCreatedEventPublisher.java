package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;

public interface SpaceshipCreatedEventPublisher {
  void publishSpaceshipCreatedEvent(SpaceshipCreatedDomainEvent event);
}