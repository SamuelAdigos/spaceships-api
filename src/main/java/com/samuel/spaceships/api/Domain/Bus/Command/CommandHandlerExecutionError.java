package com.samuel.spaceships.api.Domain.Bus.Command;

public final class CommandHandlerExecutionError extends RuntimeException {
  public CommandHandlerExecutionError(Throwable cause) {
    super(cause);
  }
}
