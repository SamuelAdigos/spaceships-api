package com.samuel.spaceships.api.Infrastructure.Persistence.Mongo;

import org.bson.conversions.Bson;

public class MongoCriteria {

  private final Bson filter;
  private final Bson sort;

  public MongoCriteria(Bson filter, Bson sort) {
    this.filter = filter;
    this.sort = sort;
  }

  public Bson getFilter() {
    return filter;
  }

  public Bson getSort() {
    return sort;
  }
}