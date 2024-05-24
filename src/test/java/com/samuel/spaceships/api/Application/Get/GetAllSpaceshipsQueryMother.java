package com.samuel.spaceships.api.Application.Get;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class GetAllSpaceshipsQueryMother {
  public static GetAllSpaceshipsQuery create(Pageable pageable) {
    return new GetAllSpaceshipsQuery(pageable);
  }

  public static GetAllSpaceshipsQuery unpaged() {
    return create(Pageable.unpaged());
  }

  public static GetAllSpaceshipsQuery random() {
    return create(PageRequest.of(0, 10));
  }
}