package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipIdMother;

public final class DeleteSpaceshipCommandMother {
  public static DeleteSpaceshipCommand create(SpaceshipId id) {
    return new DeleteSpaceshipCommand(id.value());
  }

  public static DeleteSpaceshipCommand random() {
    return create(SpaceshipIdMother.random());
  }
}
