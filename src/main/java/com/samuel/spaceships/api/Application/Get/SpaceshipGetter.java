package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.SpaceshipsPageResponse;
import com.samuel.spaceships.api.Application.SpaceshipResponse;
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
  public SpaceshipsPageResponse getAllSpaceships(Pageable pageable) {
    Page<Spaceship> spaceships = repository.findAll(pageable);
    return SpaceshipsPageResponse.fromPage(spaceships);
  }

  @Cacheable("spaceshipById")
  public SpaceshipResponse getSpaceshipById(String id) {
    Spaceship spaceship = repository.findById(new SpaceshipId(id))
        .orElseThrow(() -> new SpaceshipNotExist(new SpaceshipId(id)));
    return SpaceshipResponse.fromAggregate(spaceship);
  }

  @Cacheable("spaceshipsByName")
  public SpaceshipsPageResponse getSpaceshipsByName(String name, Pageable pageable) {
    Page<Spaceship> spaceships = repository.findByNameContaining(name, pageable);
    return SpaceshipsPageResponse.fromPage(spaceships);
  }
}