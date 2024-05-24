package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipIdMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
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

public final class GetAllSpaceshipsQueryHandlerShould extends GetSpaceshipUnitTestCase {
  private GetAllSpaceshipsQueryHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new GetAllSpaceshipsQueryHandler(getter);
  }

  @Nested
  class if_spaceships_exist {
    @BeforeEach
    protected void setUp() {
      Pageable pageable = PageRequest.of(0, 2);
      Spaceship spaceship1 = SpaceshipMother.random();
      Spaceship spaceship2 = SpaceshipMother.random();
      Page<Spaceship> expectedSpaceships = new PageImpl<>(Arrays.asList(spaceship1, spaceship2), PageRequest.of(0, 2), 2);

      when(repository.findAll(pageable)).thenReturn(expectedSpaceships);
    }

    @Test
    void get_all_spaceships_paged() {
      GetAllSpaceshipsQuery query = GetAllSpaceshipsQueryMother.random();

      handler.run(query);

      verify(repository, atLeastOnce()).findAll(query.pageable());
    }

    @Test
    void get_all_spaceships_unpaged() {
      GetAllSpaceshipsQuery query = GetAllSpaceshipsQueryMother.unpaged();

      handler.run(query);

      verify(repository, atLeastOnce()).findAll(query.pageable());
    }
  }

}