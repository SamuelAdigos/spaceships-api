package com.samuel.spaceships.api.Application;

import com.samuel.spaceships.api.Domain.Bus.Query.Response;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public final class SpaceshipsPageResponse implements Response {
  private final Page<SpaceshipResponse> spaceships;

  public static SpaceshipsPageResponse fromPage(Page<Spaceship> spaceshipPage) {
    Page<SpaceshipResponse> spaceshipResponses = spaceshipPage.map(SpaceshipResponse::fromAggregate);
    return new SpaceshipsPageResponse(spaceshipResponses);
  }
}
