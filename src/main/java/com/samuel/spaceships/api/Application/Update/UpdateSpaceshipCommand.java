package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Domain.Bus.Command.Command;

public record UpdateSpaceshipCommand(String id, String name, String franchise, double maxSpeed) implements Command {
}