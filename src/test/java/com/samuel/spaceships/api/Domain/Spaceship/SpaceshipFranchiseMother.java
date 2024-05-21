package com.samuel.spaceships.api.Domain.Spaceship;

public class SpaceshipFranchiseMother {
  public static SpaceshipFranchise withDefaults() {
    return new SpaceshipFranchise("Star Trek");
  }

  public static SpaceshipFranchise withCustomValue(String value) {
    return new SpaceshipFranchise(value);
  }
}
