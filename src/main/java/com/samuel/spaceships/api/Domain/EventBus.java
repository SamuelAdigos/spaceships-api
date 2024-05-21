package com.samuel.spaceships.api.Domain;

import java.util.List;

public interface EventBus {
  void publish(final List<DomainEvent> events);
}
