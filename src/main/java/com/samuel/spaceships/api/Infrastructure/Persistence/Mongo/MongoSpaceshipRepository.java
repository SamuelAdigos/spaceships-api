package com.samuel.spaceships.api.Infrastructure.Persistence.Mongo;

import com.mongodb.client.MongoClient;
import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public final class MongoSpaceshipRepository extends MongoRepository<Spaceship> implements
    SpaceshipRepository {

  public MongoSpaceshipRepository(MongoClient mongoClient) {
    super(mongoClient);
  }

  @Override
  public void save(Spaceship spaceship) {
    persist(spaceship.id().value(), spaceship.toPrimitives());
  }

  @Override
  public List<Spaceship> matching(Criteria criteria) {
    return searchByCriteria(criteria, Spaceship::fromPrimitives);
  }

  @Override
  public void deleteById(SpaceshipId id) {
    deleteById(id.value());
  }

  @Override
  public boolean existsById(SpaceshipId id) {
    return existsById(id.value());
  }

  @Override
  protected String moduleName() {
    return "spaceships";
  }
}
