package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceshipUpdater {

  private final SpaceshipRepository repository;

  @CacheEvict(value = "spaceshipById", key = "#id")
  public void update(Long id, String name, String franchise, double maxSpeed) {
    var spaceshipId = new SpaceshipId(id);
    if (!repository.existsById(spaceshipId)) {
      throw new SpaceshipNotExist(spaceshipId);
    }

    var spaceship = new Spaceship(id, name, franchise, maxSpeed);
    repository.save(spaceship);
  }
}