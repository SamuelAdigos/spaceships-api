package com.samuel.spaceships.api.Domain.Spaceship;

public class SpaceshipSpeedMother {
  public static SpaceshipSpeed withDefaults() {
    return new SpaceshipSpeed(9.975);
  }

  public static SpaceshipSpeed withCustomValue(double value) {
    return new SpaceshipSpeed(value);
  }
}
