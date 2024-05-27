package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import com.samuel.spaceships.api.Domain.Spaceship.Events.SpaceshipCreatedDomainEvent;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipCreatedDomainEventMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipIdMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.when;

public final class CreateSpaceshipCommandHandlerShould extends SpaceshipsModuleUnitTestCase {

  private CreateSpaceshipCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new CreateSpaceshipCommandHandler(
        new SpaceshipCreator(uuidGenerator, repository, eventBus));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "d290f1ee-6c54-4b01-90e6-d701748f0851",
      "2c74c9e8-fb9e-4c71-9d98-8b24d5e8bf0b",
      "3e470ae3-6323-4a0d-9ddf-8b7a91b1db3c"
  })
  void create_a_valid_spaceship(String idValue) {
    CreateSpaceshipCommand command = CreateSpaceshipCommandMother.random();
    Spaceship spaceship = SpaceshipMother
        .fromRequest(command)
        .id(SpaceshipIdMother.create(idValue))
        .build();
    when(uuidGenerator.generate()).thenReturn(idValue);
    SpaceshipCreatedDomainEvent domainEvent = SpaceshipCreatedDomainEventMother.fromSpaceship(
        spaceship);

    handler.run(command);

    shouldHaveSaved(spaceship);
    shouldHavePublished(domainEvent);
  }
}
