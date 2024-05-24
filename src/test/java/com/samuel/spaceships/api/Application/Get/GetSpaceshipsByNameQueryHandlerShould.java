package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipNameMother;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class GetSpaceshipsByNameQueryHandlerShould extends GetSpaceshipUnitTestCase {
  private GetSpaceshipsByNameQueryHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new GetSpaceshipsByNameQueryHandler(getter);
  }

  @Nested
  class when_spaceships_exist {

    Pageable pageable = PageRequest.of(0, 10);

    @BeforeEach
    protected void setUp() {
      Spaceship spaceship1 = SpaceshipMother
          .randomBuilder()
          .name(SpaceshipNameMother.create("Millennium Falcon"))
          .build();
      Page<Spaceship> expectedSpaceships = new PageImpl<>(Collections.singletonList(spaceship1), pageable, 1);

      when(repository.findByNameContaining("Falcon", pageable)).thenReturn(expectedSpaceships);
    }

    @Test
    void get_spaceships_by_name() {
      GetSpaceshipsByNameQuery command = GetSpaceshipsByNameQueryMother.create("Falcon", pageable);

      handler.run(command);

      verify(repository, atLeastOnce()).findByNameContaining("Falcon", command.pageable());
    }
  }

  @Nested
  class when_spaceships_with_common_name_exists {

    Pageable pageable = PageRequest.of(0, 10);

    @BeforeEach
    protected void setUp() {
      Spaceship spaceship1 = SpaceshipMother
          .randomBuilder()
          .name(SpaceshipNameMother.create("Millennium Falcon"))
          .build();
      Spaceship spaceship2 = SpaceshipMother
          .randomBuilder()
          .name(SpaceshipNameMother.create("Falcon Heavy"))
          .build();
      Page<Spaceship> expectedSpaceships = new PageImpl<>(Arrays.asList(spaceship1, spaceship2), pageable, 2);

      when(repository.findByNameContaining("Falcon", pageable)).thenReturn(expectedSpaceships);
    }

    @Test
    void get_spaceships_by_name() {
      GetSpaceshipsByNameQuery command = GetSpaceshipsByNameQueryMother.create("Falcon", pageable);

      handler.run(command);

      verify(repository, atLeastOnce()).findByNameContaining("Falcon", command.pageable());
    }
  }
}