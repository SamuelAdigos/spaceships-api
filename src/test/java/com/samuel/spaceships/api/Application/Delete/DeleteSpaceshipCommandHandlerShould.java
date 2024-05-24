package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipIdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public final class DeleteSpaceshipCommandHandlerShould extends SpaceshipsModuleUnitTestCase {
  private DeleteSpaceshipCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    SpaceshipDeleter deleter = new SpaceshipDeleter(repository);
    handler = new DeleteSpaceshipCommandHandler(deleter);
  }

  @Nested
  class when_spaceship_exist {

    @BeforeEach
    protected void setUp() {
      when(repository.existsById(any())).thenReturn(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "d290f1ee-6c54-4b01-90e6-d701748f0851",
        "2c74c9e8-fb9e-4c71-9d98-8b24d5e8bf0b",
        "3e470ae3-6323-4a0d-9ddf-8b7a91b1db3c"
    })
    void delete_a_valid_spaceship(String value) {
      SpaceshipId spaceshipId = SpaceshipIdMother.create(value);
      DeleteSpaceshipCommand command = createDeleteSpaceshipCommand(spaceshipId);

      handler.run(command);

      shouldHaveCheckedExistence(spaceshipId);
      shouldHaveDeleted(spaceshipId);
    }
  }

  private DeleteSpaceshipCommand createDeleteSpaceshipCommand(SpaceshipId spaceshipId) {
    return DeleteSpaceshipCommandMother.create(spaceshipId);
  }


}