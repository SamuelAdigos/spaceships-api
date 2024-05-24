package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchise;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchiseMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipName;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipNameMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeed;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeedMother;

public final class CreateSpaceshipCommandMother {
  public static CreateSpaceshipCommand create(SpaceshipName name, SpaceshipFranchise franchise, SpaceshipSpeed maxSpeed) {
    return new CreateSpaceshipCommand( name.value(),franchise.value(), maxSpeed.value());
  }

  public static CreateSpaceshipCommand random() {
    return create(SpaceshipNameMother.random(), SpaceshipFranchiseMother.random(), SpaceshipSpeedMother.random());
  }
}
