package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class UpdateSpaceshipCommandHandlerShould extends SpaceshipsModuleUnitTestCase {
  private UpdateSpaceshipCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new UpdateSpaceshipCommandHandler(new SpaceshipUpdater(repository));
  }

  @Test
  void update_spaceship() {
    Spaceship spaceship = SpaceshipMother.withDefaults();
    Long spaceshipId = spaceship.id();
    String newName = "New Name";

    when(repository.existsById(new SpaceshipId(spaceshipId))).thenReturn(true);

    UpdateSpaceshipCommand command = UpdateSpaceshipCommandMother.builder().withId(spaceshipId).withName(newName).build();

    handler.run(command);

    verify(repository, atLeastOnce()).save(any(Spaceship.class));
  }
}