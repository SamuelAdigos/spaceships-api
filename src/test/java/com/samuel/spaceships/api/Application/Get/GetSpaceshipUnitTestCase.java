package com.samuel.spaceships.api.Application.Get;

import com.samuel.spaceships.api.Application.SpaceshipsModuleUnitTestCase;
import org.junit.jupiter.api.BeforeEach;

public class GetSpaceshipUnitTestCase extends SpaceshipsModuleUnitTestCase {

  SpaceshipGetter getter;

  @BeforeEach
  protected void setUp() {
    super.setUp();

    getter = new SpaceshipGetter(repository);
  }
}
