package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.CommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSpaceshipByIdCommandHandler extends
    CommandHandler<GetSpaceshipByIdCommand, Spaceship> {
  private final SpaceshipGetter spaceshipGetter;
  private static final Logger logger = LoggerFactory.getLogger(
      GetSpaceshipByIdCommandHandler.class);

  @Override
  protected void log(GetSpaceshipByIdCommand command) {
    logger.info("Attempting to get spaceship with id \"{}\"", command.id());
  }

  @Override
  protected Spaceship run(GetSpaceshipByIdCommand command) {
    return spaceshipGetter.getSpaceshipById(command.id());
  }
}