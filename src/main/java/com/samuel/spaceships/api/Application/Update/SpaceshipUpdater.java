package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchise;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipName;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeed;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceshipUpdater {

  private final SpaceshipRepository repository;

  @CacheEvict(value = "spaceshipsByCriteria")
  public void update(SpaceshipId id, SpaceshipName name, SpaceshipFranchise franchise,
      SpaceshipSpeed maxSpeed) {
    if (!repository.existsById(id)) {
      throw new SpaceshipNotExist(id);
    }

    var spaceship = new Spaceship(id, name, franchise, maxSpeed);
    repository.save(spaceship);
  }
}