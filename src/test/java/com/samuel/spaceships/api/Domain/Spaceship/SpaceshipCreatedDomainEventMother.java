package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;

public class SpaceshipCreatedDomainEventMother {
  public static SpaceshipCreatedDomainEvent create(SpaceshipId id, SpaceshipName name, SpaceshipFranchise franchise, SpaceshipSpeed maxSpeed) {
    return new SpaceshipCreatedDomainEvent(id.value(), name.value(), franchise.value(), maxSpeed.value());
  }

  public static SpaceshipCreatedDomainEvent fromSpaceship(Spaceship spaceship) {
    return new SpaceshipCreatedDomainEvent(spaceship.id().value(), spaceship.name().value(), spaceship.franchise().value(), spaceship.maxSpeed().value());
  }

  public static SpaceshipCreatedDomainEvent random() {
    return create(SpaceshipIdMother.random(), SpaceshipNameMother.random(), SpaceshipFranchiseMother.random(), SpaceshipSpeedMother.random());
  }
}
