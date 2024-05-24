package com.samuel.spaceships.api.Domain.Bus.Command;

public interface CommandBus {
  void dispatch(Command command) throws CommandHandlerExecutionError;
}
