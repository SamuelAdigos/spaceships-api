package com.samuel.spaceships.api.Domain.Spaceship;

import java.util.Random;

public class SpaceshipSpeedMother {
  public static SpaceshipSpeed create(double value) {
    return new SpaceshipSpeed(value);
  }

  public static SpaceshipSpeed random() {
    return new SpaceshipSpeed(createRandomDouble());
  }

  private static double createRandomDouble() {
    Random random = new Random();
    double result = random.nextDouble() * 10;
    return Math.round(result * 100.0) / 100.0;
  }
}
