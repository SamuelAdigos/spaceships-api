package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Application.Create.CreateSpaceshipCommand;
import com.samuel.spaceships.api.Application.Update.UpdateSpaceshipCommand;

public class SpaceshipMother {
  public static Spaceship create(SpaceshipId id, SpaceshipName name, SpaceshipFranchise franchise, SpaceshipSpeed speed) {
    return new Builder()
        .id(id)
        .name(name)
        .franchise(franchise)
        .speed(speed)
        .build();
  }

  public static Spaceship random() {
    return new Builder()
        .id(SpaceshipIdMother.random())
        .name(SpaceshipNameMother.random())
        .franchise(SpaceshipFranchiseMother.random())
        .speed(SpaceshipSpeedMother.random())
        .build();
  }

  public static Builder randomBuilder() {
    return new Builder()
        .id(SpaceshipIdMother.random())
        .name(SpaceshipNameMother.random())
        .franchise(SpaceshipFranchiseMother.random())
        .speed(SpaceshipSpeedMother.random());
  }

  public static Builder fromRequest(CreateSpaceshipCommand command) {
    return new Builder()
        .id(SpaceshipIdMother.random())
        .name(SpaceshipNameMother.create(command.name()))
        .franchise(SpaceshipFranchiseMother.create(command.franchise()))
        .speed(SpaceshipSpeedMother.create(command.maxSpeed()));
  }

  public static Builder fromRequest(UpdateSpaceshipCommand command) {
    return new Builder()
        .id(SpaceshipIdMother.create(command.id()))
        .name(SpaceshipNameMother.create(command.name()))
        .franchise(SpaceshipFranchiseMother.create(command.franchise()))
        .speed(SpaceshipSpeedMother.create(command.maxSpeed()));
  }

  public static class Builder {
    private SpaceshipId id;
    private SpaceshipName name;
    private SpaceshipFranchise franchise;
    private SpaceshipSpeed speed;

    public Builder id(SpaceshipId id) {
      this.id = id;
      return this;
    }

    public Builder name(SpaceshipName name) {
      this.name = name;
      return this;
    }

    public Builder franchise(SpaceshipFranchise franchise) {
      this.franchise = franchise;
      return this;
    }

    public Builder speed(SpaceshipSpeed speed) {
      this.speed = speed;
      return this;
    }

    public Spaceship build() {
      return new Spaceship(id, name, franchise, speed);
    }
  }
}
