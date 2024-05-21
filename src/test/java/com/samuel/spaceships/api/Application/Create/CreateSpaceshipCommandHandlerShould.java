package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipCreatedDomainEventMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public final class CreateSpaceshipCommandHandlerShould extends SpaceshipsModuleUnitTestCase {
  private CreateSpaceshipCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new CreateSpaceshipCommandHandler(new SpaceshipCreator(idGenerator, repository, eventBus));
  }

  @Test
  void create_a_valid_spaceship() {
    final Long ID_EXPECTED = 1L;
    when(idGenerator.generate()).thenReturn(ID_EXPECTED);
    CreateSpaceshipCommand command = CreateSpaceshipCommandMother.withDefaults();

    Spaceship spaceship = SpaceshipMother.fromRequest(command, ID_EXPECTED);
    SpaceshipCreatedDomainEvent domainEvent = SpaceshipCreatedDomainEventMother.fromSpaceship(spaceship);

    handler.run(command);

    shouldHaveSaved(spaceship);
    shouldHavePublished(domainEvent);
  }


}
