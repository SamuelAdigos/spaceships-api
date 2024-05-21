package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceshipDeleter {

  private final SpaceshipRepository repository;

  @CacheEvict(value = "spaceshipById", key = "#id")
  public Void delete(Long id) {
    SpaceshipId spaceshipId = new SpaceshipId(id);
    if (!repository.existsById(spaceshipId)) {
      throw new SpaceshipNotExist(spaceshipId);
    }

    repository.deleteById(spaceshipId);
    return null;
  }
}