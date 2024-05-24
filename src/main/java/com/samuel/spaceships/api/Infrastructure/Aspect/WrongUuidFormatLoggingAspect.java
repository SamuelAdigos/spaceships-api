package com.samuel.spaceships.api.Infrastructure.Aspect;


import java.util.UUID;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WrongUuidFormatLoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(WrongUuidFormatLoggingAspect.class);

  @Before("execution(* com.samuel.spaceships.api.Application.Get.SpaceshipGetter.getSpaceshipById(..)) && args(id)")
  public void logWrongFormat(String id) {
    if (!isValidUUID(id)) {
      logger.warn("Attempted to get a spaceship with an invalid UUID format: {}", id);
    }
  }

  private boolean isValidUUID(String uuid) {
    try {
      UUID.fromString(uuid);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}