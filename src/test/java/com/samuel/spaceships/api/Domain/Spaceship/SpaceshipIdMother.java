package com.samuel.spaceships.api.Domain.Spaceship;

public class SpaceshipIdMother {
  public static SpaceshipId withDefaults() {
    return new SpaceshipId(1L);
  }

  public static SpaceshipId withCustomValue(Long value) {
    return new SpaceshipId(value);
  }
}
