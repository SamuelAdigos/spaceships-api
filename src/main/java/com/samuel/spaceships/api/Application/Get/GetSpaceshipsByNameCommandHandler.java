package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.CommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSpaceshipsByNameCommandHandler extends
    CommandHandler<GetSpaceshipsByNameCommand, Page<Spaceship>> {
  private final SpaceshipGetter spaceshipGetter;
  private static final Logger logger = LoggerFactory.getLogger(GetSpaceshipsByNameCommandHandler.class);

  @Override
  protected void log(GetSpaceshipsByNameCommand command) {
    logger.info("Getting all spaceships who contains \"{}\"", command.name());
  }

  @Override
  protected Page<Spaceship> run(GetSpaceshipsByNameCommand command) {
    return spaceshipGetter.getSpaceshipsByName(command.name(), command.pageable());
  }
}