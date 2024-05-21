package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.Command;

public record GetSpaceshipByIdCommand(Long id) implements Command {
}