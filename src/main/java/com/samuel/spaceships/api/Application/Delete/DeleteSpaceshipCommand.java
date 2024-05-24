package com.samuel.spaceships.api.Application.Delete;

import com.samuel.spaceships.api.Domain.Bus.Command.Command;

public record DeleteSpaceshipCommand(String id) implements Command {
}
