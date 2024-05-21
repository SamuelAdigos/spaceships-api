package com.samuel.spaceships.api.Infrastructure;

import com.samuel.spaceships.api.Domain.IdGenerator;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseIdGenerator implements IdGenerator {

    private final SpaceshipRepository repository;

    public DatabaseIdGenerator(SpaceshipRepository repository) {
      this.repository = repository;
    }

    @Override
    public Long generate() {
      Long maxId = repository.findMaxId();
      return (maxId == null) ? 0L : maxId + 1L;
    }
}
