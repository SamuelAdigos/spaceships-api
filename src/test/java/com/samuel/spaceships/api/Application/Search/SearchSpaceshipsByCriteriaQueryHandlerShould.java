package com.samuel.spaceships.api.Application.Search;

import com.samuel.spaceships.api.Application.SpaceshipResponse;
import com.samuel.spaceships.api.Application.SpaceshipsResponse;
import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipCriteriaMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchSpaceshipsByCriteriaQueryHandlerShould extends SearchSpaceshipUnitTestCase {

  private SearchSpaceshipsByCriteriaQueryHandler handler;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    handler = new SearchSpaceshipsByCriteriaQueryHandler(searcher);
  }

  @Nested
  class if_spaceships_exist {

    Criteria criteria;

    @BeforeEach
    protected void setUp() {
      Spaceship spaceship1 = SpaceshipMother.random();
      Spaceship spaceship2 = SpaceshipMother.random();
      List<Spaceship> expectedSpaceships = Arrays.asList(spaceship1, spaceship2);
      criteria = SpaceshipCriteriaMother.emptyCriteria();

      when(repository.matching(criteria)).thenReturn(expectedSpaceships);
    }

    @Test
    void get_all_spaceships_without_criteria() {
      SearchSpaceshipsByCriteriaQuery query = SearchSpaceshipsByCriteriaQueryMother.withNoCriteria();

      handler.run(query);

      verify(repository, atLeastOnce()).matching(criteria);
    }
  }

  @Nested
  class if_no_spaceships_exist {

    Criteria criteria;

    @BeforeEach
    protected void setUp() {
      criteria = SpaceshipCriteriaMother.emptyCriteria();

      when(repository.matching(criteria)).thenReturn(Collections.emptyList());
    }

    @Test
    void return_empty_spaceships_list() {
      SearchSpaceshipsByCriteriaQuery query = SearchSpaceshipsByCriteriaQueryMother.withNoCriteria();

      SpaceshipsResponse response = handler.run(query);

      assertTrue(response.spaceships().isEmpty());
      verify(repository, atLeastOnce()).matching(criteria);
    }
  }

  @Nested
  class when_limit_and_offset_are_provided {

    Criteria criteria;

    private final int limit = 2;

    private final int offset = 0;

    @BeforeEach
    protected void setUp() {
      Spaceship spaceship1 = SpaceshipMother.random();
      Spaceship spaceship2 = SpaceshipMother.random();
      List<Spaceship> expectedSpaceships = Arrays.asList(spaceship1, spaceship2);
      criteria = SpaceshipCriteriaMother.withLimitAndOffset(limit, offset);

      when(repository.matching(criteria)).thenReturn(expectedSpaceships);
    }

    @Test
    void return_limited_spaceships_list() {
      SearchSpaceshipsByCriteriaQuery query = SearchSpaceshipsByCriteriaQueryMother.withLimitAndOffset(
          limit, offset);

      SpaceshipsResponse response = handler.run(query);

      assertEquals(query.limit().get(), response.spaceships().size());
      verify(repository, atLeastOnce()).matching(criteria);
    }
  }

  @Nested
  class when_order_is_provided {

    Criteria criteria;

    String orderBy = "name";

    String orderType = "ASC";

    @BeforeEach
    protected void setUp() {
      Spaceship spaceship1 = SpaceshipMother.random();
      Spaceship spaceship2 = SpaceshipMother.random();
      List<Spaceship> expectedSpaceships = Arrays.asList(spaceship1, spaceship2);
      criteria = SpaceshipCriteriaMother.withOrder(orderBy, orderType);

      when(repository.matching(criteria)).thenReturn(expectedSpaceships);
    }

    @Test
    void return_ordered_spaceships_list() {
      SearchSpaceshipsByCriteriaQuery query = SearchSpaceshipsByCriteriaQueryMother.withOrder(
          orderBy, orderType);

      SpaceshipsResponse response = handler.run(query);

      List<SpaceshipResponse> sortedSpaceships = response.spaceships();
      sortedSpaceships.sort(Comparator.comparing(SpaceshipResponse::getName));

      assertEquals(sortedSpaceships, response.spaceships());
      verify(repository, atLeastOnce()).matching(criteria);
    }
  }

}
