package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class GetSpaceshipsByNameCommandHandlerShould extends GetSpaceshipUnitTestCase {
  private GetSpaceshipsByNameCommandHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new GetSpaceshipsByNameCommandHandler(getter);
  }

  @Test
  void get_spaceships_by_name() {
    Pageable pageable = PageRequest.of(0, 2);
    String spaceshipName = "Falcon";
    Spaceship spaceship1 = SpaceshipMother.builder().withName("Millennium Falcon").build();
    Page<Spaceship> expectedSpaceships = new PageImpl<>(Arrays.asList(spaceship1), PageRequest.of(0, 2), 1);

    when(repository.findByNameContaining(spaceshipName, pageable)).thenReturn(expectedSpaceships);

    GetSpaceshipsByNameCommand command = GetSpaceshipsByNameCommandMother.builder().withName(spaceshipName).build();

    handler.run(command);

    verify(repository, atLeastOnce()).findByNameContaining(spaceshipName, pageable);
  }
}