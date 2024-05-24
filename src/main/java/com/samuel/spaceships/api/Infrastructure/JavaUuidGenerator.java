package com.samuel.spaceships.api.Infrastructure;

import com.samuel.spaceships.api.Domain.UuidGenerator;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
  @Override
  public String generate() {
    return UUID.randomUUID().toString();
  }
}
