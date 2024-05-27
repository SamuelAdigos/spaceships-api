package com.samuel.spaceships.api.Application.Search;

import com.samuel.spaceships.api.Domain.Bus.Query.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SearchSpaceshipsByCriteriaQuery implements Query {

  private final List<HashMap<String, String>> filters;
  private final Optional<String> orderBy;
  private final Optional<String> orderType;
  private final Optional<Integer> limit;
  private final Optional<Integer> offset;

  public SearchSpaceshipsByCriteriaQuery(
      List<HashMap<String, String>> filters,
      Optional<String> orderBy,
      Optional<String> orderType,
      Optional<Integer> limit,
      Optional<Integer> offset
  ) {
    this.filters = filters;
    this.orderBy = orderBy;
    this.orderType = orderType;
    this.limit = limit;
    this.offset = offset;
  }

  public List<HashMap<String, String>> filters() {
    return filters;
  }

  public Optional<String> orderBy() {
    return orderBy;
  }

  public Optional<String> orderType() {
    return orderType;
  }

  public Optional<Integer> limit() {
    return limit;
  }

  public Optional<Integer> offset() {
    return offset;
  }

}
