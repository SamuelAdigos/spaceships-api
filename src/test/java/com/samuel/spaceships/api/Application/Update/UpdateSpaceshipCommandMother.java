package com.samuel.spaceships.api.Application.Update;

public class UpdateSpaceshipCommandMother {
  public static UpdateSpaceshipCommand withDefaults() {
    return new UpdateSpaceshipCommand(1L, "Default Name", "Default Franchise", 2.0);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long id = 1L;
    private String name = "Default Name";
    private String franchise = "Default Franchise";
    private Double maxSpeed = 2.0;

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

    public Builder withMaxSpeed(Double maxSpeed) {
      this.maxSpeed = maxSpeed;
      return this;
    }

    public UpdateSpaceshipCommand build() {
      return new UpdateSpaceshipCommand(id, name, franchise, maxSpeed);
    }
  }
}