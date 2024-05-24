package com.samuel.spaceships.api.Infrastructure.Persistence.Mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseSpaceshipMongoRepository extends MongoRepository<SpaceshipData, String> {
  Page<SpaceshipData> findByNameContaining(String name, Pageable pageable);
}


