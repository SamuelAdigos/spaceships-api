package com.samuel.spaceships.api.Application.Get;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class GetAllSpaceshipsCommandMother {
  public static GetAllSpaceshipsCommand withDefaults() {
    return new GetAllSpaceshipsCommand(PageRequest.of(0, 2));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Pageable pageable = PageRequest.of(0, 2);

    public Builder withPageable(Pageable pageable) {
      this.pageable = pageable;
      return this;
    }

    public GetAllSpaceshipsCommand build() {
      return new GetAllSpaceshipsCommand(pageable);
    }
  }
}