package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.SpaceshipsPageResponse;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllSpaceshipsQueryHandler extends
    QueryHandler<GetAllSpaceshipsQuery, SpaceshipsPageResponse> {
  private final SpaceshipGetter spaceshipGetter;
  private static final Logger logger = LoggerFactory.getLogger(
      GetAllSpaceshipsQueryHandler.class);


  @Override
  protected void log(GetAllSpaceshipsQuery command) {
    logger.info("Getting all spaceships");
  }

  @Override
  protected SpaceshipsPageResponse run(GetAllSpaceshipsQuery command) {
    Pageable pageable = command.pageable() != null ? command.pageable() : Pageable.unpaged();
    return spaceshipGetter.getAllSpaceships(pageable);
  }
}