package com.samuel.spaceships.api.Infrastructure.Aspect;


import com.samuel.spaceships.api.Domain.Criteria.FilterOperator;
import com.samuel.spaceships.api.Domain.Criteria.Filters;
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

  @Before("execution(* com.samuel.spaceships.api.Application.Search.SpaceshipsByCriteriaSearcher.search(..)) && args(filters,..)")
  public void checkFiltersForId(Filters filters) {
    filters.filters().stream()
        .filter(filter ->
            filter.field().value().trim().equalsIgnoreCase("id") && filter.operator().value()
                .trim().toLowerCase().equals(FilterOperator.EQUAL.value()))
        .forEach(filter -> {
          System.out.println("Filter value: " + filter.value().value());
          logWrongFormat(filter.value().value());
        });
  }

  private void logWrongFormat(String value) {
    if (!isValidUUID(value)) {
      logger.warn("The UUID {} is not in the correct format", value);
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