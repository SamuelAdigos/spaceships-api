package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.DoubleValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class SpaceshipSpeed extends DoubleValueObject {
  public SpaceshipSpeed(double value) {
    super(value);
  }

  public SpaceshipSpeed() {
    super(0.0);
  }
}