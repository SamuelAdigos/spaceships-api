package com.samuel.spaceships.api.Infrastructure;

import com.samuel.spaceships.api.Infrastructure.Persistence.Mapper.SpaceshipMapper;
import com.samuel.spaceships.api.Infrastructure.Persistence.H2SpaceshipRepository;
import com.samuel.spaceships.api.Infrastructure.Persistence.SpaceshipJpaRepository;
import com.samuel.spaceships.api.SpaceshipsApiApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpaceshipsApiApplication.class)
@SpringBootTest
public abstract class SpaceshipsModuleInfrastructureTestCase {

  @Autowired
  protected SpaceshipJpaRepository spaceshipJpaRepository;
  @Autowired
  protected SpaceshipMapper spaceshipMapper;
  @Autowired
  protected H2SpaceshipRepository h2SpaceshipRepository;

  private final int MAX_ATTEMPTS                   = 3;
  private final int MILLIS_TO_WAIT_BETWEEN_RETRIES = 300;

  protected void eventually(Runnable assertion) throws Exception {
    int     attempts = 0;
    boolean allOk    = false;

    while (attempts < MAX_ATTEMPTS && !allOk) {
      try {
        assertion.run();

        allOk = true;
      } catch (Throwable error) {
        attempts++;

        if (attempts > MAX_ATTEMPTS) {
          throw new Exception(
              String.format("Could not assert after some retries. Last error: %s", error.getMessage())
          );
        }

        Thread.sleep(MILLIS_TO_WAIT_BETWEEN_RETRIES);
      }
    }
  }
}
