package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Domain.Bus.Query.Query;
import org.springframework.data.domain.Pageable;

public record GetSpaceshipsByNameQuery(String name, Pageable pageable) implements Query {
}