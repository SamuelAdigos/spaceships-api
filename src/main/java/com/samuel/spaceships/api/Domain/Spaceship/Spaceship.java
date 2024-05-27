package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.AggregateRoot;
import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Spaceship extends AggregateRoot {

  private final SpaceshipId id;
  private final SpaceshipName name;
  private final SpaceshipFranchise franchise;
  private final SpaceshipSpeed maxSpeed;

  public Spaceship(SpaceshipId id, SpaceshipName name, SpaceshipFranchise franchise,
      SpaceshipSpeed maxSpeed) {
    this.id = id;
    this.name = name;
    this.franchise = franchise;
    this.maxSpeed = maxSpeed;
  }

  public static Spaceship create(SpaceshipId id, SpaceshipName name, SpaceshipFranchise franchise,
      SpaceshipSpeed maxSpeed) {
    Spaceship spaceship = new Spaceship(id, name, franchise, maxSpeed);

    spaceship.record(new SpaceshipCreatedDomainEvent(id.value(), name.value(), franchise.value(),
        maxSpeed.value()));

    return spaceship;
  }

  public SpaceshipId id() {
    return id;
  }

  public SpaceshipName name() {
    return name;
  }

  public SpaceshipFranchise franchise() {
    return franchise;
  }

  public SpaceshipSpeed maxSpeed() {
    return maxSpeed;
  }

  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<>() {{
      put("_id", id.value());
      put("name", name.value());
      put("franchise", franchise.value());
      put("maxSpeed", maxSpeed.value());
    }};
  }

  public static Spaceship fromPrimitives(Map<String, Object> plainData) {
    return new Spaceship(
        new SpaceshipId((String) plainData.get("_id")),
        new SpaceshipName((String) plainData.get("name")),
        new SpaceshipFranchise((String) plainData.get("franchise")),
        new SpaceshipSpeed((Double) plainData.get("maxSpeed"))
    );
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
