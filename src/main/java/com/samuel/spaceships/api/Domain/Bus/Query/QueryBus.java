package com.samuel.spaceships.api.Domain.Bus.Query;

public interface QueryBus {
  <R> R ask(Query query) throws QueryHandlerExecutionError;
}