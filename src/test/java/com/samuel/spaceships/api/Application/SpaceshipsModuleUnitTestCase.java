package com.samuel.spaceships.api.Application;

import com.samuel.spaceships.api.Domain.DomainEvent;
import com.samuel.spaceships.api.Domain.EventBus;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.UuidGenerator;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class SpaceshipsModuleUnitTestCase {
  protected UuidGenerator uuidGenerator;
  protected SpaceshipRepository repository;
  protected EventBus eventBus;

  @BeforeEach
  protected void setUp() {
    uuidGenerator = mock(UuidGenerator.class);
    repository = mock(SpaceshipRepository.class);
    eventBus = mock(EventBus.class);
  }

  public void shouldHaveSaved(Spaceship spaceship) {
    verify(repository, atLeastOnce()).save(spaceship);
  }

  public void shouldHaveCheckedExistence(SpaceshipId spaceshipId) {
    verify(repository, atLeastOnce()).existsById(spaceshipId);
  }

  public void shouldHaveDeleted(SpaceshipId spaceshipId) {
    verify(repository, atLeastOnce()).deleteById(spaceshipId);
  }

  public void shouldHavePublished(List<DomainEvent> domainEvents) {
    verify(eventBus, atLeastOnce()).publish(domainEvents);
  }

  public void shouldHavePublished(DomainEvent domainEvent) {
    shouldHavePublished(Collections.singletonList(domainEvent));
  }
}
