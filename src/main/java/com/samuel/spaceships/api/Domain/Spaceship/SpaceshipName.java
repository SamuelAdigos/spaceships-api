package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class SpaceshipName extends StringValueObject {
  public SpaceshipName(String value) {
    super(value);
  }

  public SpaceshipName() {
    super("");
  }
}
