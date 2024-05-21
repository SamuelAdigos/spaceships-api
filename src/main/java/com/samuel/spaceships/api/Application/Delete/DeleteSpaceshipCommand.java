package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Application.Command;

public record DeleteSpaceshipCommand(Long id) implements Command {
}
