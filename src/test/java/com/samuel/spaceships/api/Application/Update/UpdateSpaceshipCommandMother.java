package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchise;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchiseMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipIdMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipName;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipNameMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeed;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeedMother;

public final class UpdateSpaceshipCommandMother {
  public static UpdateSpaceshipCommand create(SpaceshipId id, SpaceshipName name, SpaceshipFranchise franchise, SpaceshipSpeed maxSpeed) {
    return new UpdateSpaceshipCommand(id.value(), name.value(),franchise.value(), maxSpeed.value());
  }

  public static UpdateSpaceshipCommand random() {
    return create(SpaceshipIdMother.random(), SpaceshipNameMother.random(), SpaceshipFranchiseMother.random(), SpaceshipSpeedMother.random());
  }
}