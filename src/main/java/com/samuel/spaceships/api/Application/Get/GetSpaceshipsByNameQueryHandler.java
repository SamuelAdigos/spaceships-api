package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.SpaceshipsPageResponse;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSpaceshipsByNameQueryHandler extends
    QueryHandler<GetSpaceshipsByNameQuery, SpaceshipsPageResponse> {
  private final SpaceshipGetter spaceshipGetter;
  private static final Logger logger = LoggerFactory.getLogger(GetSpaceshipsByNameQueryHandler.class);

  @Override
  protected void log(GetSpaceshipsByNameQuery command) {
    logger.info("Getting all spaceships who contains \"{}\"", command.name());
  }

  @Override
  protected SpaceshipsPageResponse run(GetSpaceshipsByNameQuery command) {
    return spaceshipGetter.getSpaceshipsByName(command.name(), command.pageable());
  }
}