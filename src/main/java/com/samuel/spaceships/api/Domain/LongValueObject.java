package com.samuel.spaceships.api.Domain;

public abstract class LongValueObject {
  private final Long value;

  protected LongValueObject(Long value) {
    this.value = value;
  }

  public Long value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LongValueObject that = (LongValueObject) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}