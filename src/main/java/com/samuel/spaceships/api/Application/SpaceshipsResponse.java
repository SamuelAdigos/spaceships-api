package com.samuel.spaceships.api.Application;

import com.samuel.spaceships.api.Domain.Bus.Query.Response;
import java.util.List;

public class SpaceshipsResponse implements Response {

  private final List<SpaceshipResponse> spaceships;

  public SpaceshipsResponse(List<SpaceshipResponse> spaceships) {
    this.spaceships = spaceships;
  }

  public List<SpaceshipResponse> spaceships() {
    return spaceships;
  }
}
