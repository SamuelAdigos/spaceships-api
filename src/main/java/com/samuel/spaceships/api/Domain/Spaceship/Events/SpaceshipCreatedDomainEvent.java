package com.samuel.spaceships.api.Domain.Spaceship.Events;

import com.samuel.spaceships.api.Domain.DomainEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class SpaceshipCreatedDomainEvent extends DomainEvent {
  private String name;
  private String franchise;
  private double maxSpeed;

  public SpaceshipCreatedDomainEvent() {
    super(null);
  }

  public SpaceshipCreatedDomainEvent(String aggregateId, String name, String franchise, double maxSpeed) {
    super(aggregateId);
    this.name = name;
    this.franchise = franchise;
    this.maxSpeed = maxSpeed;
  }

  public SpaceshipCreatedDomainEvent(String aggregateId, String eventId, String occurredOn, String name, String franchise, double maxSpeed) {
    super(aggregateId, eventId, occurredOn);
    this.name = name;
    this.franchise = franchise;
    this.maxSpeed = maxSpeed;
  }

  @Override
  public String eventName() {
    return "spaceship.created";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<String, Serializable>() {{
      put("name", name);
      put("franchise", franchise);
    }};
  }

  @Override
  public SpaceshipCreatedDomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
    return new SpaceshipCreatedDomainEvent(aggregateId, eventId, occurredOn, (String) body.get("name"), (String) body.get("franchise"), (double) body.get("maxSpeed"));
  }

  public String getName() {
    return name;
  }

  public String getFranchise() {
    return franchise;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpaceshipCreatedDomainEvent that = (SpaceshipCreatedDomainEvent) o;
    return name.equals(that.name) && franchise.equals(that.franchise);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, franchise);
  }
}