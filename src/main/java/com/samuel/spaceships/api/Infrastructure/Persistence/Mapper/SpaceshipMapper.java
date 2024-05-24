package com.samuel.spaceships.api.Infrastructure.Persistence.Mapper;


import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchise;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipName;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeed;
import com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipMapper {

  public Spaceship toEntity(SpaceshipData data) {
    if (data == null) {
      return null;
    }
    SpaceshipId id = data.getId() == null ? null : new SpaceshipId(data.getId());
    SpaceshipName name = data.getName() == null ? null : new SpaceshipName(data.getName());
    SpaceshipFranchise franchise = data.getFranchise() == null ? null : new SpaceshipFranchise(data.getFranchise());
    SpaceshipSpeed maxSpeed = new SpaceshipSpeed(data.getMaxSpeed());

    return new Spaceship(id, name, franchise, maxSpeed);
  }

  public SpaceshipData toData(Spaceship entity) {
    if (entity == null) {
      return null;
    }
    return new SpaceshipData(entity.id().value(), entity.name().value(), entity.franchise().value(), entity.maxSpeed().value());
  }
}