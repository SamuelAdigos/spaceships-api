package com.samuel.spaceships.api.Domain;

import java.util.UUID;

public final class UuidMother {
  public static String random() {
    return UUID.randomUUID().toString();
  }
}
