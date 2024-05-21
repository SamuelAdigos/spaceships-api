package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.LongValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class SpaceshipId extends LongValueObject {
  public SpaceshipId(Long value) {
    super(value);
  }

  public SpaceshipId() {
    super(0L);
  }
}
