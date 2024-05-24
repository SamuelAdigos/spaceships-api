package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Domain.Bus.Command.CommandHandler;

import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchise;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipName;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSpaceshipCommandHandler extends
    CommandHandler<CreateSpaceshipCommand> {

  private final SpaceshipCreator spaceshipCreator;
  private static final Logger logger = LoggerFactory.getLogger(CreateSpaceshipCommandHandler.class);


  @Override
  protected void log(CreateSpaceshipCommand command) {
    logger.info("Starship created successful!");
  }

  @Override
  protected void run(CreateSpaceshipCommand command) {
    SpaceshipName name = new SpaceshipName(command.name());
    SpaceshipFranchise franchise = new SpaceshipFranchise(command.franchise());
    SpaceshipSpeed maxSpeed = new SpaceshipSpeed(command.maxSpeed());

    spaceshipCreator.create(name, franchise, maxSpeed);
  }
}