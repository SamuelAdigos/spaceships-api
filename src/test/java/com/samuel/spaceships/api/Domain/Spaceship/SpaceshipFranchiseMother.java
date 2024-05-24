package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.WordMother;

public class SpaceshipFranchiseMother {
  public static SpaceshipFranchise create(String value) {
    return new SpaceshipFranchise(value);
  }

  public static SpaceshipFranchise random() {
    return new SpaceshipFranchise(WordMother.random());
  }
}
