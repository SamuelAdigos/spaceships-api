package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Domain.EventBus;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchise;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipName;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeed;
import com.samuel.spaceships.api.Domain.UuidGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceshipCreator {

  private final UuidGenerator uuidGenerator;
  private final SpaceshipRepository repository;
  private final EventBus eventBus;
  private static final Logger logger = LoggerFactory.getLogger(SpaceshipCreator.class);



  public void create(SpaceshipName name, SpaceshipFranchise franchise, SpaceshipSpeed maxSpeed) {
    SpaceshipId id = generateUniqueSpaceshipId();

    Spaceship spaceship = Spaceship.create(id, name, franchise, maxSpeed);

    logger.debug("Creating starship with uuid \"{}\"...", id.value());
    repository.save(spaceship);
    eventBus.publish(spaceship.pullDomainEvents());
  }

  private SpaceshipId generateUniqueSpaceshipId() {
    SpaceshipId id;
    do {
      id = new SpaceshipId(uuidGenerator.generate());
    } while (repository.existsById(id));
    return id;
  }
}