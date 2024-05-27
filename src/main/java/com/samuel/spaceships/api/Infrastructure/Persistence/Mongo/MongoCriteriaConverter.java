package com.samuel.spaceships.api.Infrastructure.Persistence.Mongo;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import com.samuel.spaceships.api.Domain.Criteria.Filter;
import com.samuel.spaceships.api.Domain.Criteria.FilterOperator;
import java.util.HashMap;
import java.util.function.Function;
import org.bson.Document;
import org.bson.conversions.Bson;

public final class MongoCriteriaConverter {

  private final HashMap<FilterOperator, Function<Filter, Bson>> queryTransformers = new HashMap<>() {{
    put(FilterOperator.EQUAL, MongoCriteriaConverter.this::equalCriteria);
    put(FilterOperator.NOT_EQUAL, MongoCriteriaConverter.this::notEqualCriteria);
    put(FilterOperator.GT, MongoCriteriaConverter.this::greaterThanCriteria);
    put(FilterOperator.LT, MongoCriteriaConverter.this::lessThanCriteria);
    put(FilterOperator.CONTAINS, MongoCriteriaConverter.this::containsCriteria);
  }};

  public MongoCriteria convert(Criteria criteria) {
    Bson filter = new Document();
    if (criteria.hasFilters()) {
      for (Filter criteriaFilter : criteria.filters().filters()) {
        Bson mongoCriteria = criteriaForFilter(criteriaFilter);
        filter = Filters.and(filter, mongoCriteria);
      }
    }

    Bson sort = new Document();
    if (!criteria.order().orderType().isNone()) {
      sort = Sorts.orderBy(
          new Document(criteria.order().orderBy().value(), criteria.order().orderType().value()));
    }
    return new MongoCriteria(filter, sort);
  }

  private Bson criteriaForFilter(Filter filter) {
    Function<Filter, Bson> transformer = queryTransformers.get(filter.operator());
    return transformer.apply(filter);
  }

  private Bson equalCriteria(Filter filter) {
    return Filters.eq(translateField(filter.field().value()), filter.value().value());
  }

  private Bson notEqualCriteria(Filter filter) {
    return Filters.ne(translateField(filter.field().value()), filter.value().value());
  }

  private Bson greaterThanCriteria(Filter filter) {
    return Filters.gt(translateField(filter.field().value()), filter.value().value());
  }

  private Bson lessThanCriteria(Filter filter) {
    return Filters.lt(translateField(filter.field().value()), filter.value().value());
  }

  private Bson containsCriteria(Filter filter) {
    return Filters.regex(translateField(filter.field().value()), filter.value().value());
  }

  private String translateField(String field) {
    if ("id".equals(field)) {
      return "_id";
    }
    return field;
  }
}