package com.samuel.spaceships.api.Application.Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SearchSpaceshipsByCriteriaQueryMother {

  public static SearchSpaceshipsByCriteriaQuery create(List<HashMap<String, String>> filters,
      String orderBy, String order, int limit, int offset) {
    return new SearchSpaceshipsByCriteriaQuery(filters, Optional.of(orderBy), Optional.of(order),
        Optional.of(limit), Optional.of(offset));
  }

  public static SearchSpaceshipsByCriteriaQuery withNoCriteria() {
    return new SearchSpaceshipsByCriteriaQuery(new ArrayList<>(), Optional.empty(),
        Optional.empty(),
        Optional.empty(), Optional.empty());
  }

  public static SearchSpaceshipsByCriteriaQuery withLimitAndOffset(int limitValue,
      int offsetValue) {
    List<HashMap<String, String>> filters = new ArrayList<>();
    Optional<String> orderBy = Optional.empty();
    Optional<String> orderType = Optional.empty();
    Optional<Integer> limit = Optional.of(limitValue);
    Optional<Integer> offset = Optional.of(offsetValue);

    return new SearchSpaceshipsByCriteriaQuery(filters, orderBy, orderType, limit, offset);
  }

  public static SearchSpaceshipsByCriteriaQuery withOrder(String fieldName, String type) {
    List<HashMap<String, String>> filters = new ArrayList<>();
    Optional<String> orderBy = Optional.of(fieldName);
    Optional<String> orderType = Optional.of(type);
    Optional<Integer> limit = Optional.empty();
    Optional<Integer> offset = Optional.empty();

    return new SearchSpaceshipsByCriteriaQuery(filters, orderBy, orderType, limit, offset);
  }
}
