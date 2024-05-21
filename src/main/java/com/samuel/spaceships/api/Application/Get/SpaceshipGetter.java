package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceshipGetter {
  private final SpaceshipRepository repository;

  @Cacheable("spaceships")
  public Page<Spaceship> getAllSpaceships(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Cacheable("spaceshipById")
  public Spaceship getSpaceshipById(Long id) {
    return repository.findById(new SpaceshipId(id)).orElseThrow(() -> new SpaceshipNotExist(new SpaceshipId(id)));
  }

  @Cacheable("spaceshipsByName")
  public Page<Spaceship> getSpaceshipsByName(String name, Pageable pageable) {
    return repository.findByNameContaining(name, pageable);
  }
}