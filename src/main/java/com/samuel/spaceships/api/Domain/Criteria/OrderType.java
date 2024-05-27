package com.samuel.spaceships.api.Domain.Criteria;

public enum OrderType {
  ASC(1),
  DESC(-1),
  NONE(0);

  private final int order;

  OrderType(int order) {
    this.order = order;
  }

  public static OrderType fromValue(String value) {
    for (OrderType orderType : values()) {
      if (orderType.name().equalsIgnoreCase(value)) {
        return orderType;
      }
    }
    return null;
  }

  public int value() {
    return order;
  }

  public boolean isNone() {
    return this == NONE;
  }
}
