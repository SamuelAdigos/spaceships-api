package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Application.Create.CreateSpaceshipCommand;

public class SpaceshipMother {

  public static Spaceship withDefaults() {
    return new Spaceship(1L, "Enterprise", "Star Trek", 9.975);
  }

  public static Spaceship fromRequest(CreateSpaceshipCommand command, Long idExpected) {
    return new Spaceship(idExpected, command.name(), command.franchise(), command.maxSpeed());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long id = 1L;
    private String name = "Enterprise";
    private String franchise = "Star Trek";
    private double maxSpeed = 9.975;

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withFranchise(String franchise) {
      this.franchise = franchise;
      return this;
    }

    public Builder withMaxSpeed(double maxSpeed) {
      this.maxSpeed = maxSpeed;
      return this;
    }

    public Spaceship build() {
      return new Spaceship(id, name, franchise, maxSpeed);
    }
  }
}
