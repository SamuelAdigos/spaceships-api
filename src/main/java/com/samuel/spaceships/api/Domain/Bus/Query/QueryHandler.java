package com.samuel.spaceships.api.Domain.Bus.Query;


public abstract class QueryHandler<Q extends Query, R extends Response> {
  public R execute(Q query) {
    R response = this.run(query);
    this.log(query);
    return response;
  }

  abstract protected void log(Q query);
  abstract protected R run(Q query);
}
