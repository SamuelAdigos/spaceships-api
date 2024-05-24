package com.samuel.spaceships.api.Application;

import com.samuel.spaceships.api.Domain.Bus.Query.Response;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class SpaceshipResponse implements Response {
  private final String id;
  private final String name;
  private final String franchise;
  private final double maxSpeed;

  public static SpaceshipResponse fromAggregate(Spaceship spaceship) {
    return new SpaceshipResponse(spaceship.id().value(), spaceship.name().value(), spaceship.franchise().value(), spaceship.maxSpeed().value());
  }
}
