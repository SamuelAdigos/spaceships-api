package com.samuel.spaceships.api.Application.Search;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import org.junit.jupiter.api.BeforeEach;

public class SearchSpaceshipUnitTestCase extends SpaceshipsModuleUnitTestCase {

  SpaceshipsByCriteriaSearcher searcher;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    searcher = new SpaceshipsByCriteriaSearcher(repository);
  }
}
