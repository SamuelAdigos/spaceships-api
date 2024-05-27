package com.samuel.spaceships.api.Domain.Spaceship.Repository;

import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import java.util.List;

public interface SpaceshipRepository {

  void save(Spaceship spaceship);

  List<Spaceship> matching(Criteria criteria);

  void deleteById(SpaceshipId id);

  boolean existsById(SpaceshipId id);
}
