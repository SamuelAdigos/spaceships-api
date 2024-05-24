package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class GetSpaceshipByIdQueryHandlerShould extends GetSpaceshipUnitTestCase {
  private GetSpaceshipByIdQueryHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new GetSpaceshipByIdQueryHandler(getter);
  }

  @Nested
  class when_spaceship_exist {

    @BeforeEach
    protected void setUp() {
      Spaceship spaceship = SpaceshipMother.random();

      when(repository.findById(any())).thenReturn(Optional.of(spaceship));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "d290f1ee-6c54-4b01-90e6-d701748f0851",
        "2c74c9e8-fb9e-4c71-9d98-8b24d5e8bf0b",
        "3e470ae3-6323-4a0d-9ddf-8b7a91b1db3c"
    })
    void get_spaceship_by_id(String value) {
      GetSpaceshipByIdQuery query = GetSpaceshipByIdQueryMother.create(value);

      handler.run(query);

      verify(repository, atLeastOnce()).findById(new SpaceshipId(value));
    }
  }
}