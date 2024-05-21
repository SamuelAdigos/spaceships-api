package com.samuel.spaceships.api.Application.Create;

import com.samuel.spaceships.api.Application.Command;

public record CreateSpaceshipCommand(String name, String franchise, double maxSpeed) implements Command {
}