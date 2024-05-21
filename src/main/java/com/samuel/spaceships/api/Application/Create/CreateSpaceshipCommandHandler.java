package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Application.CommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSpaceshipCommandHandler extends
    CommandHandler<CreateSpaceshipCommand, Spaceship> {

  private final SpaceshipCreator spaceshipCreator;
  private static final Logger logger = LoggerFactory.getLogger(CreateSpaceshipCommandHandler.class);


  @Override
  protected void log(CreateSpaceshipCommand command) {
    logger.info("Starship with name \"{}\" created!", command.name());
  }

  @Override
  protected Spaceship run(CreateSpaceshipCommand command) {
    return spaceshipCreator.create(command.name(), command.franchise(), command.maxSpeed());
  }
}