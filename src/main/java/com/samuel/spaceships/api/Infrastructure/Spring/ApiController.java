package com.samuel.spaceships.api.Infrastructure.Spring;

import com.samuel.spaceships.api.Domain.Bus.Command.Command;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandBus;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandHandlerExecutionError;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandNotRegisteredError;
import com.samuel.spaceships.api.Domain.Bus.Query.Query;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryBus;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandlerExecutionError;
import com.samuel.spaceships.api.Domain.DomainError;
import java.util.HashMap;
import org.springframework.http.HttpStatus;

public abstract class ApiController {
  private final QueryBus queryBus;
  private final CommandBus commandBus;

  public ApiController(QueryBus queryBus, CommandBus commandBus) {
    this.queryBus   = queryBus;
    this.commandBus = commandBus;
  }

  protected void dispatch(Command command) throws CommandHandlerExecutionError, CommandNotRegisteredError {
    commandBus.dispatch(command);
  }

  protected <R> R ask(Query query) throws QueryHandlerExecutionError {
    return queryBus.ask(query);
  }

  abstract public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping();
}
