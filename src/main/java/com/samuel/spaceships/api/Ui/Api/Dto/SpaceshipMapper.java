package com.samuel.spaceships.api.Ui.Api.Dto;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import org.springframework.stereotype.Component;

@Component("dtoSpaceshipMapper")
public class SpaceshipMapper {

  public SpaceshipDto toDto(Spaceship spaceship) {
    if (spaceship == null) {
      return null;
    }

    return new SpaceshipDto(
        spaceship.id(),
        spaceship.name(),
        spaceship.franchise(),
        spaceship.maxSpeed()
    );
  }

  public Spaceship toEntity(SpaceshipDto spaceshipDto) {
    if (spaceshipDto == null) {
      return null;
    }

    return new Spaceship(
        spaceshipDto.id(),
        spaceshipDto.name(),
        spaceshipDto.franchise(),
        spaceshipDto.maxSpeed()
    );
  }
}