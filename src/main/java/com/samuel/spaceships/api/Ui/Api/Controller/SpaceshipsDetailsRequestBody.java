package com.samuel.spaceships.api.Ui.Api.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class SpaceshipsDetailsRequestBody {
  @NotEmpty @JsonProperty(required = true) String name;
  @NotEmpty @JsonProperty(required = true) String franchise;
  @NotNull @JsonProperty(required = true) double maxSpeed;
}
