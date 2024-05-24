package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Bus.Query.Query;

public record GetSpaceshipByIdQuery(String id) implements Query {
}