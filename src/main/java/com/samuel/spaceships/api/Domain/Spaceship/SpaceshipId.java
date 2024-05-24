package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.Identifier;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class SpaceshipId extends Identifier {
  public SpaceshipId(String value) {
    super(value);
  }

  public SpaceshipId() {
  }
}
