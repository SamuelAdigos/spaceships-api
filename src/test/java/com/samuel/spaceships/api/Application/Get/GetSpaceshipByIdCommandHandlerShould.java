package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class GetSpaceshipByIdCommandHandlerShould extends GetSpaceshipUnitTestCase {
  private GetSpaceshipByIdCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new GetSpaceshipByIdCommandHandler(getter);
  }

  @Test
  void get_spaceship_by_id() {
    Spaceship spaceship = SpaceshipMother.withDefaults();
    Long spaceshipId = spaceship.id();

    when(repository.findById(new SpaceshipId(spaceshipId))).thenReturn(Optional.of(spaceship));

    GetSpaceshipByIdCommand command = new GetSpaceshipByIdCommand(spaceshipId);

    handler.run(command);

    verify(repository, atLeastOnce()).findById(new SpaceshipId(spaceshipId));
  }
}