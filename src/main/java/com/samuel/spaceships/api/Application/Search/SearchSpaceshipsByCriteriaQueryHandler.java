package com.samuel.spaceships.api.Application.Search;

import com.samuel.spaceships.api.Application.SpaceshipsResponse;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandler;
import com.samuel.spaceships.api.Domain.Criteria.Filters;
import com.samuel.spaceships.api.Domain.Criteria.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SearchSpaceshipsByCriteriaQueryHandler extends
    QueryHandler<SearchSpaceshipsByCriteriaQuery, SpaceshipsResponse> {

  private final SpaceshipsByCriteriaSearcher searcher;
  private static final Logger logger = LoggerFactory.getLogger(
      SearchSpaceshipsByCriteriaQueryHandler.class);

  public SearchSpaceshipsByCriteriaQueryHandler(SpaceshipsByCriteriaSearcher searcher) {
    this.searcher = searcher;
  }

  @Override
  protected void log(SearchSpaceshipsByCriteriaQuery query) {
    logger.info("Getting all spaceships by criteria");
  }

  @Override
  protected SpaceshipsResponse run(SearchSpaceshipsByCriteriaQuery query) {
    Filters filters = Filters.fromValues(query.filters());
    Order order = Order.fromValues(query.orderBy(), query.orderType());

    return searcher.search(filters, order, query.limit(), query.offset());
  }
}
