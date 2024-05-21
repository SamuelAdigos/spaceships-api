package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;

public class SpaceshipCreatedDomainEventMother {
  public static SpaceshipCreatedDomainEvent fromSpaceship(Spaceship spaceship) {
    return new SpaceshipCreatedDomainEvent(spaceship.id(), spaceship.name(), spaceship.franchise());
  }
}
