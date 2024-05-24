package com.samuel.spaceships.api.Application.Get;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.samuel.spaceships.api.Domain.WordMother.random;
import static org.springframework.data.domain.Pageable.unpaged;


public final class GetSpaceshipsByNameQueryMother {
  public static GetSpaceshipsByNameQuery create(String name, Pageable pageable) {
    return new GetSpaceshipsByNameQuery(name, pageable);
  }

  public static GetSpaceshipsByNameQuery randomPaged() {
    return create(random(), PageRequest.of(0, 10));
  }

  public static GetSpaceshipsByNameQuery randomUnpaged() {
    return create(random(), unpaged());
  }
}