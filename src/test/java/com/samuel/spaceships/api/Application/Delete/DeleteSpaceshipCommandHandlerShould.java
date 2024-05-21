package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class DeleteSpaceshipCommandHandlerShould extends SpaceshipsModuleUnitTestCase {
  private DeleteSpaceshipCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new DeleteSpaceshipCommandHandler(new SpaceshipDeleter(repository));
  }

  @Test
  void delete_a_valid_spaceship() {
    final Long CASE_SPACESHIP_ID = 1L;
    when(repository.existsById(new SpaceshipId(CASE_SPACESHIP_ID))).thenReturn(true);
    DeleteSpaceshipCommand command = DeleteSpaceshipCommandMother.builder().withId(CASE_SPACESHIP_ID).build();

    handler.run(command);

    verify(repository, atLeastOnce()).deleteById(new SpaceshipId(CASE_SPACESHIP_ID));
  }
}