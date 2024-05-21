package com.samuel.spaceships.api.Domain.Spaceship;

public class SpaceshipNameMother {
  public static SpaceshipName withDefaults() {
    return new SpaceshipName("Enterprise");
  }

  public static SpaceshipName withCustomValue(String value) {
    return new SpaceshipName(value);
  }
}
