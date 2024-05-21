package com.samuel.spaceships.api.Infrastructure.Persistence.Mapper;


import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Infrastructure.Persistence.SpaceshipData;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipMapper {

  public Spaceship toEntity(SpaceshipData data) {
    if (data == null) {
      return null;
    }
    return new Spaceship(data.getId(), data.getName(), data.getFranchise(), data.getMaxSpeed());
  }

  public SpaceshipData toData(Spaceship entity) {
    if (entity == null) {
      return null;
    }
    return new SpaceshipData(entity.id(), entity.name(), entity.franchise(), entity.maxSpeed());
  }
}