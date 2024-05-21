package com.samuel.spaceships.api.Domain.Spaceship.Events;

import com.samuel.spaceships.api.Domain.DomainEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class SpaceshipCreatedDomainEvent extends DomainEvent {
  private String name;
  private String franchise;

  public SpaceshipCreatedDomainEvent() {
    super(null);
  }

  public SpaceshipCreatedDomainEvent(Long aggregateId, String name, String franchise) {
    super(aggregateId);
    this.name = name;
    this.franchise = franchise;
  }

  public SpaceshipCreatedDomainEvent(Long aggregateId, String eventId, String occurredOn, String name, String franchise) {
    super(aggregateId, eventId, occurredOn);
    this.name = name;
    this.franchise = franchise;
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
  public SpaceshipCreatedDomainEvent fromPrimitives(Long aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
    return new SpaceshipCreatedDomainEvent(aggregateId, eventId, occurredOn, (String) body.get("name"), (String) body.get("franchise"));
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