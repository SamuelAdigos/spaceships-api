package com.samuel.spaceships.api.Ui.Api.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SpaceshipsPutControllerRequestBody(
    @NotEmpty @JsonProperty(required = true) String id,
    @NotEmpty @JsonProperty(required = true) String name,
    @NotEmpty @JsonProperty(required = true) String franchise,
    @NotNull @JsonProperty(required = true) double maxSpeed) {
}