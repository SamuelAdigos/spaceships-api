package com.samuel.spaceships.api.Domain.Spaceship.Repository;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SpaceshipRepository {
  Spaceship save(Spaceship spaceship);

  Optional<Spaceship> findById(SpaceshipId id);

  Long findMaxId();

  Page<Spaceship> findAll(Pageable pageable);

  Page<Spaceship> findByNameContaining(String name, Pageable pageable);

  void deleteById(SpaceshipId id);

  boolean existsById(SpaceshipId id);
}