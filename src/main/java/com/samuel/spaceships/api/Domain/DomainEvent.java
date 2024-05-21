package com.samuel.spaceships.api.Domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public abstract class DomainEvent {
  private Long aggregateId;
  private String eventId;
  private String occurredOn;

  protected DomainEvent() {
    // Constructor sin parámetros necesario para la deserialización de Jackson
  }

  public DomainEvent(Long aggregateId) {
    this.aggregateId = aggregateId;
    this.eventId = UUID.randomUUID().toString();
    this.occurredOn = LocalDateTime.now().toString();
  }

  public DomainEvent(Long aggregateId, String eventId, String occurredOn) {
    this.aggregateId = aggregateId;
    this.eventId = eventId;
    this.occurredOn = occurredOn;
  }

  public abstract String eventName();

  public abstract HashMap<String, Serializable> toPrimitives();

  public abstract DomainEvent fromPrimitives(
      Long aggregateId,
      HashMap<String, Serializable> body,
      String eventId,
      String occurredOn
  );

  public Long aggregateId() {
    return aggregateId;
  }

  public String eventId() {
    return eventId;
  }

  public String occurredOn() {
    return occurredOn;
  }
}