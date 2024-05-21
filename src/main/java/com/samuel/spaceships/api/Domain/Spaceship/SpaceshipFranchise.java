package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.StringValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class SpaceshipFranchise extends StringValueObject {
  public SpaceshipFranchise(String value) {
    super(value);
  }

  public SpaceshipFranchise() {
    super("");
  }
}
