package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Application.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSpaceshipCommandHandler extends
    CommandHandler<UpdateSpaceshipCommand, Void> {

  private final SpaceshipUpdater spaceshipUpdater;
  private static final Logger logger = LoggerFactory.getLogger(
      UpdateSpaceshipCommandHandler.class);

  @Override
  protected void log(UpdateSpaceshipCommand command) {
    logger.info("Starship with id \"{}\" has been updated!", command.id());
  }

  @Override
  protected Void run(UpdateSpaceshipCommand command) {
    spaceshipUpdater.update(command.id(), command.name(), command.franchise(), command.maxSpeed());
    return null;
  }
}