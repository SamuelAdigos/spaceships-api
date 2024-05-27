package com.samuel.spaceships.api.Domain.Spaceship;

import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import com.samuel.spaceships.api.Domain.Criteria.Filter;
import com.samuel.spaceships.api.Domain.Criteria.Filters;
import com.samuel.spaceships.api.Domain.Criteria.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SpaceshipCriteriaMother {

  public static Criteria emptyCriteria() {
    return new Criteria(new Filters(new ArrayList<>()), Order.none());
  }

  public static Criteria nameAndFranchiseContains(String name, String franchise) {
    Filter nameFilter = Filter.create("name", "contains", name);
    Filter franchiseFilter = Filter.create("franchise", "contains", franchise);

    return new Criteria(new Filters(Arrays.asList(nameFilter, franchiseFilter)), Order.asc("name"));
  }

  public static Criteria nameContains(String name) {
    Filter nameFilter = Filter.create("name", "contains", name);

    return new Criteria(new Filters(List.of(nameFilter)), Order.none());
  }

  public static Criteria idEquals(String id) {
    Filter idFilter = Filter.create("id", "equals", id);

    return new Criteria(new Filters(List.of(idFilter)), Order.none());
  }

  public static Criteria withLimitAndOffset(int limit, int offset) {
    return new Criteria(new Filters(new ArrayList<>()), Order.none(), Optional.of(limit),
        Optional.of(offset));
  }

  public static Criteria withOrder(String orderBy, String orderType) {
    return new Criteria(new Filters(new ArrayList<>()), Order.fromValues(Optional.of(orderBy),
        Optional.of(orderType)));
  }
}
