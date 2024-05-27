package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceshipDeleter {

  private final SpaceshipRepository repository;

  @CacheEvict(value = "spaceshipsByCriteria")
  public void delete(String id) {
    SpaceshipId spaceshipId = new SpaceshipId(id);
    if (!repository.existsById(spaceshipId)) {
      throw new SpaceshipNotExist(spaceshipId);
    }

    repository.deleteById(spaceshipId);
  }
}