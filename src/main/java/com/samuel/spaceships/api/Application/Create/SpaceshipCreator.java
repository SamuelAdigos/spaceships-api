package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Domain.EventBus;
import com.samuel.spaceships.api.Domain.IdGenerator;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceshipCreator {

  private final IdGenerator idGenerator;
  private final SpaceshipRepository repository;
  private final EventBus eventBus;


  public Spaceship create(String name, String franchise, double maxSpeed) {
    Spaceship spaceship = Spaceship.create(idGenerator.generate(), name, franchise, maxSpeed);
    repository.save(spaceship);

    eventBus.publish(spaceship.pullDomainEvents());

    return spaceship;
  }
}