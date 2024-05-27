package com.samuel.spaceships.api.Domain.Criteria;

public enum FilterOperator {
  EQUAL("$eq"),
  NOT_EQUAL("$ne"),
  GT("$gt"),
  LT("$lt"),
  CONTAINS("$regex");

  private final String operator;

  FilterOperator(String operator) {
    this.operator = operator;
  }

  public static FilterOperator fromValue(String value) {
    for (FilterOperator operator : values()) {
      if (operator.name().equalsIgnoreCase(value)) {
        return operator;
      }
    }
    return null;
  }

  public String value() {
    return operator;
  }
}