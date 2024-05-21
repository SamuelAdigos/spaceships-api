package com.samuel.spaceships.api.Domain.Spaceship.Errors;

import com.samuel.spaceships.api.Domain.DomainError;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;

public final class SpaceshipNotExist extends DomainError {
  public SpaceshipNotExist(SpaceshipId id) {
    super("spaceship_not_exist", String.format("The spaceship <%s> doesn't exist", id.value()));
  }
}
