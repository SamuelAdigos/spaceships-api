package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Application.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSpaceshipCommandHandler extends
    CommandHandler<DeleteSpaceshipCommand, Void> {

  private final SpaceshipDeleter spaceshipDeleter;
  private static final Logger logger = LoggerFactory.getLogger(DeleteSpaceshipCommandHandler.class);


  @Override
  protected void log(DeleteSpaceshipCommand command) {
    logger.info("Attempted to delete starship with id \"{}\"", command.id());
  }

  @Override
  protected Void run(DeleteSpaceshipCommand command) {
    return spaceshipDeleter.delete(command.id());
  }
}