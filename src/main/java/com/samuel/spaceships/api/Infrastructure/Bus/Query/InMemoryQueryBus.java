package com.samuel.spaceships.api.Infrastructure.Bus.Query;


import com.samuel.spaceships.api.Domain.Bus.Query.Query;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryBus;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandler;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandlerExecutionError;
import com.samuel.spaceships.api.Domain.Bus.Query.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public final class InMemoryQueryBus implements QueryBus {
  private final QueryHandlersInformation information;
  private final ApplicationContext context;

  public InMemoryQueryBus(QueryHandlersInformation information, ApplicationContext context) {
    this.information = information;
    this.context     = context;
  }

  @Override
  public Response ask(Query query) throws QueryHandlerExecutionError {
    try {
      Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());

      QueryHandler handler = context.getBean(queryHandlerClass);

      return handler.execute(query);
    } catch (Throwable error) {
      throw new QueryHandlerExecutionError(error);
    }
  }
}
