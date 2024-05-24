package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.SpaceshipResponse;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSpaceshipByIdQueryHandler extends
    QueryHandler<GetSpaceshipByIdQuery, SpaceshipResponse> {
  private final SpaceshipGetter spaceshipGetter;
  private static final Logger logger = LoggerFactory.getLogger(
      GetSpaceshipByIdQueryHandler.class);

  @Override
  protected void log(GetSpaceshipByIdQuery query) {
    logger.info("Attempting to get spaceship with id \"{}\"", query.id());
  }

  @Override
  protected SpaceshipResponse run(GetSpaceshipByIdQuery query) {
    return spaceshipGetter.getSpaceshipById(query.id());
  }
}