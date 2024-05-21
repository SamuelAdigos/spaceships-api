package com.samuel.spaceships.api.Infrastructure.Aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NegativeIdLoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(NegativeIdLoggingAspect.class);

  @Before("execution(* com.samuel.spaceships.api.Application.Get.SpaceshipGetter.getSpaceshipById(..)) && args(id)")
  public void logNegativeId(Long id) {
    if (id < 0) {
      logger.warn("Attempted to get a spaceship with a negative id: {}", id);
    }
  }
}