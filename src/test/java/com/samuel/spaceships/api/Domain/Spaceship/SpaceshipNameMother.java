package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.WordMother;

public class SpaceshipNameMother {
  public static SpaceshipName create(String value) {
    return new SpaceshipName(value);
  }

  public static SpaceshipName random() {
    return new SpaceshipName(WordMother.random());
  }
}
