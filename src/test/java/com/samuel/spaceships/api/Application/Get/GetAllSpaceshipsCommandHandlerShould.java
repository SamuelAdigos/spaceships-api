package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class GetAllSpaceshipsCommandHandlerShould extends GetSpaceshipUnitTestCase {
  private GetAllSpaceshipsCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new GetAllSpaceshipsCommandHandler(getter);
  }

  @Test
  void get_all_spaceships() {
    Pageable pageable = PageRequest.of(0, 2);
    Spaceship spaceship1 = SpaceshipMother.builder().withId(1L).build();
    Spaceship spaceship2 = SpaceshipMother.builder().withId(2L).build();
    Page<Spaceship> expectedSpaceships = new PageImpl<>(Arrays.asList(spaceship1, spaceship2), PageRequest.of(0, 2), 2);

    when(repository.findAll(pageable)).thenReturn(expectedSpaceships);

    GetAllSpaceshipsCommand command = GetAllSpaceshipsCommandMother.withDefaults();

    handler.run(command);

    verify(repository, atLeastOnce()).findAll(pageable);
  }
}