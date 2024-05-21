package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.CommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllSpaceshipsCommandHandler extends
    CommandHandler<GetAllSpaceshipsCommand, Page<Spaceship>>  {
  private final SpaceshipGetter spaceshipGetter;
  private static final Logger logger = LoggerFactory.getLogger(
      GetAllSpaceshipsCommandHandler.class);


  @Override
  protected void log(GetAllSpaceshipsCommand command) {
    logger.info("Getting all spaceships");
  }

  @Override
  protected Page<Spaceship> run(GetAllSpaceshipsCommand command) {
    Pageable pageable = command.pageable() != null ? command.pageable() : Pageable.unpaged();
    return spaceshipGetter.getAllSpaceships(pageable);
  }
}