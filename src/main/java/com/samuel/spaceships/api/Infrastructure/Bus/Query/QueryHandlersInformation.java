package com.samuel.spaceships.api.Infrastructure.Bus.Query;

import com.samuel.spaceships.api.Domain.Bus.Query.Query;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandler;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryNotRegisteredError;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

@Service
public final class QueryHandlersInformation {
  HashMap<Class<? extends Query>, Class<? extends QueryHandler>> indexedQueryHandlers;

  public QueryHandlersInformation() {
    Reflections reflections = new Reflections("com.samuel");
    Set<Class<? extends QueryHandler>> classes     = reflections.getSubTypesOf(QueryHandler.class);

    indexedQueryHandlers = formatHandlers(classes);
  }

  public Class<? extends QueryHandler> search(Class<? extends Query> queryClass) throws QueryNotRegisteredError {
    Class<? extends QueryHandler> queryHandlerClass = indexedQueryHandlers.get(queryClass);

    if (null == queryHandlerClass) {
      throw new QueryNotRegisteredError(queryClass);
    }

    return queryHandlerClass;
  }

  private HashMap<Class<? extends Query>, Class<? extends QueryHandler>> formatHandlers(
      Set<Class<? extends QueryHandler>> queryHandlers
  ) {
    HashMap<Class<? extends Query>, Class<? extends QueryHandler>> handlers = new HashMap<>();

    for (Class<? extends QueryHandler> handler : queryHandlers) {
      Type genericSuperclass = handler.getGenericSuperclass();
      if (genericSuperclass instanceof ParameterizedType) {
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Class<? extends Query> queryClass = (Class<? extends Query>) paramType.getActualTypeArguments()[0];

        handlers.put(queryClass, handler);
      }
    }

    return handlers;
  }
}
