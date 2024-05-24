package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public final class UpdateSpaceshipCommandHandlerShould extends SpaceshipsModuleUnitTestCase {
  private UpdateSpaceshipCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new UpdateSpaceshipCommandHandler(new SpaceshipUpdater(repository));
  }

  @Nested
  class when_spaceship_exist {

    @BeforeEach
    protected void setUp() {
      when(repository.existsById(any())).thenReturn(true);
    }

    @Test
    void update_spaceship() {
      UpdateSpaceshipCommand command = UpdateSpaceshipCommandMother.random();

      Spaceship spaceship = SpaceshipMother.fromRequest(command).build();
      handler.run(command);

      shouldHaveCheckedExistence(spaceship.id());
      shouldHaveSaved(spaceship);
    }
  }


}