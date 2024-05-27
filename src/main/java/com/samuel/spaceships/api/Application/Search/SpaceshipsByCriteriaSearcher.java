package com.samuel.spaceships.api.Application.Search;

import com.samuel.spaceships.api.Application.SpaceshipResponse;
import com.samuel.spaceships.api.Application.SpaceshipsResponse;
import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import com.samuel.spaceships.api.Domain.Criteria.Filters;
import com.samuel.spaceships.api.Domain.Criteria.Order;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SpaceshipsByCriteriaSearcher {

  private final SpaceshipRepository repository;

  public SpaceshipsByCriteriaSearcher(SpaceshipRepository repository) {
    this.repository = repository;
  }

  @Cacheable(value = "spaceshipsByCriteria")
  public SpaceshipsResponse search(
      Filters filters,
      Order order,
      Optional<Integer> limit,
      Optional<Integer> offset
  ) {
    Criteria criteria = new Criteria(filters, order, limit, offset);

    return new SpaceshipsResponse(
        repository.matching(criteria)
            .stream()
            .map(SpaceshipResponse::fromAggregate)
            .collect(Collectors.toList())
    );
  }
}
