package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.Command;
import org.springframework.data.domain.Pageable;

public record GetAllSpaceshipsCommand(Pageable pageable) implements Command {
}