package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.UuidMother;

public class SpaceshipIdMother {
  public static SpaceshipId create(String value) {
    return new SpaceshipId(value);
  }

  public static SpaceshipId random() {
    return new SpaceshipId(UuidMother.random());
  }
}
