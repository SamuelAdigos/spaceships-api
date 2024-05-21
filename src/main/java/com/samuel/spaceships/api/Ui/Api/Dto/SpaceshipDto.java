package com.samuel.spaceships.api.Ui.Api.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpaceshipDto(
    @JsonProperty
    Long id,
    @JsonProperty
    String name,
    @JsonProperty
    String franchise,
    @JsonProperty
    double maxSpeed) {
}
