package com.samuel.spaceships.api.Application.Get;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class GetSpaceshipsByNameCommandMother {
  public static GetSpaceshipsByNameCommand withDefaults() {
    return new GetSpaceshipsByNameCommand("Default Name", PageRequest.of(0, 2));
  }

  public static GetSpaceshipsByNameCommandMother.Builder builder() {
    return new GetSpaceshipsByNameCommandMother.Builder();
  }

  public static class Builder {
    private String name = "Default Name";
    private Pageable pageable = PageRequest.of(0, 2);

    public GetSpaceshipsByNameCommandMother.Builder withName(String name) {
      this.name = name;
      return this;
    }

    public GetSpaceshipsByNameCommandMother.Builder withPageable(Pageable pageable) {
      this.pageable = pageable;
      return this;
    }

    public GetSpaceshipsByNameCommand build() {
      return new GetSpaceshipsByNameCommand(name, pageable);
    }
  }
}