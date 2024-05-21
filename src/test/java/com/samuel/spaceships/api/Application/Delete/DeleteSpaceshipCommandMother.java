package com.samuel.spaceships.api.Application.Delete;

public class DeleteSpaceshipCommandMother {
  public static DeleteSpaceshipCommand withDefaults() {
    return new DeleteSpaceshipCommand(1L);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long id = 1L;

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public DeleteSpaceshipCommand build() {
      return new DeleteSpaceshipCommand(id);
    }
  }
}
