package com.samuel.spaceships.api.Infrastructure.Persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpaceshipJpaRepository extends JpaRepository<SpaceshipData, Long> {
  Page<SpaceshipData> findAll(Pageable pageable);

  Page<SpaceshipData> findByNameContaining(String name, Pageable pageable);

  @Query("SELECT MAX(id) FROM SpaceshipData")
  Long findMaxId();
}
