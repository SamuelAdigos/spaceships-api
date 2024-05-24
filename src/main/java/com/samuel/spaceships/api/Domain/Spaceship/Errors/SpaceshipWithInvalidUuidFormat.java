package com.samuel.spaceships.api.Domain.Spaceship.Errors;

import com.samuel.spaceships.api.Domain.DomainError;

public final class SpaceshipWithInvalidUuidFormat extends DomainError {

  public SpaceshipWithInvalidUuidFormat(String malformedUuid) {
    super("spaceship_with_invalid_uuid_format",
        String.format("Spaceship with malformed Uuid: <%s> ", malformedUuid));
  }
}