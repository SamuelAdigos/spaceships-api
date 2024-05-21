package com.samuel.spaceships.api.Application.Create;

public class CreateSpaceshipCommandMother {
  public static CreateSpaceshipCommand withDefaults() {
    return new CreateSpaceshipCommand("Enterprise", "Star Trek", 9.975);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String name = "Enterprise";
    private String franchise = "Star Trek";
    private double maxSpeed = 9.975;

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

    public CreateSpaceshipCommand build() {
      return new CreateSpaceshipCommand(name, franchise, maxSpeed);
    }
  }
}
