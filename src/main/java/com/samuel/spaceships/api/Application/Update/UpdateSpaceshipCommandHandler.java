package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Domain.Bus.Command.CommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipFranchise;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipName;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipSpeed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSpaceshipCommandHandler extends
    CommandHandler<UpdateSpaceshipCommand> {

  private final SpaceshipUpdater spaceshipUpdater;
  private static final Logger logger = LoggerFactory.getLogger(
      UpdateSpaceshipCommandHandler.class);

  @Override
  protected void log(UpdateSpaceshipCommand command) {
    logger.info("Starship with id \"{}\" has been updated!", command.id());
  }

  @Override
  protected void run(UpdateSpaceshipCommand command) {
    SpaceshipId id = new SpaceshipId(command.id());
    SpaceshipName name = new SpaceshipName(command.name());
    SpaceshipFranchise franchise = new SpaceshipFranchise(command.franchise());
    SpaceshipSpeed maxSpeed = new SpaceshipSpeed(command.maxSpeed());

    spaceshipUpdater.update(id, name, franchise, maxSpeed);
  }
}