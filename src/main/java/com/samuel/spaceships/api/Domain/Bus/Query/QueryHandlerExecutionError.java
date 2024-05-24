package com.samuel.spaceships.api.Domain.Bus.Query;

public final class QueryHandlerExecutionError extends RuntimeException {
  public QueryHandlerExecutionError(Throwable cause) {
    super(cause);
  }
}
