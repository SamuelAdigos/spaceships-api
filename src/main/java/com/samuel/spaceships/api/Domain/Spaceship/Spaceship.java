package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.AggregateRoot;
import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;
import java.util.Objects;

public class Spaceship extends AggregateRoot {
  private final SpaceshipId id;
  private final SpaceshipName name;
  private final SpaceshipFranchise franchise;
  private final SpaceshipSpeed maxSpeed;

  public Spaceship(Long id, String name, String franchise, double maxSpeed) {
    this.id = new SpaceshipId(id);
    this.name = new SpaceshipName(name);
    this.franchise = new SpaceshipFranchise(franchise);
    this.maxSpeed = new SpaceshipSpeed(maxSpeed);
  }

  public static Spaceship create(Long id, String name, String franchise, double maxSpeed) {
    Spaceship spaceship = new Spaceship(id, name, franchise, maxSpeed);

    spaceship.record(new SpaceshipCreatedDomainEvent(id, name, franchise));

    return spaceship;
  }

  public Long id() {
    return id.value();
  }

  public String name() {
    return name.value();
  }

  public String franchise() {
    return franchise.value();
  }

  public double maxSpeed() {
    return maxSpeed.value();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Spaceship spaceship = (Spaceship) o;
    return id.equals(spaceship.id) &&
        name.equals(spaceship.name) &&
        franchise.equals(spaceship.franchise) &&
        maxSpeed.equals(spaceship.maxSpeed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, franchise, maxSpeed);
  }
}
