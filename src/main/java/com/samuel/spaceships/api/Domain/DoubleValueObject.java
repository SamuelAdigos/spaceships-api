package com.samuel.spaceships.api.Domain;

public abstract class DoubleValueObject {
  private final double value;

  protected DoubleValueObject(double value) {
    this.value = value;
  }

  public double value() {
    return value;
  }
}