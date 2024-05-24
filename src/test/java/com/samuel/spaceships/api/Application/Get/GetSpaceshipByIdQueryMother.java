package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.UuidMother;


public final class GetSpaceshipByIdQueryMother {
  public static GetSpaceshipByIdQuery create(String id) {
    return new GetSpaceshipByIdQuery(id);
  }

  public static GetSpaceshipByIdQuery random() {
    return create(UuidMother.random());
  }
}
