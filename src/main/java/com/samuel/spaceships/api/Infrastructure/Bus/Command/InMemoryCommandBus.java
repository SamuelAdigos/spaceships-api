package com.samuel.spaceships.api.Infrastructure.Bus.Command;

import com.samuel.spaceships.api.Domain.Bus.Command.Command;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandBus;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandHandler;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandHandlerExecutionError;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public final class InMemoryCommandBus implements CommandBus {
  private final CommandHandlersInformation information;
  private final ApplicationContext context;

  public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
    this.information = information;
    this.context     = context;
  }

  @Override
  public void dispatch(Command command) throws CommandHandlerExecutionError {
    try {
      Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

      CommandHandler handler = context.getBean(commandHandlerClass);

      handler.execute(command);
    } catch (Throwable error) {
      throw new CommandHandlerExecutionError(error);
    }
  }
}
