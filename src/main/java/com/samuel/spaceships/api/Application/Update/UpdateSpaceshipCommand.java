package com.samuel.spaceships.api.Application.Update;

import com.samuel.spaceships.api.Application.Command;

public record UpdateSpaceshipCommand(Long id, String name, String franchise, double maxSpeed) implements Command {
}