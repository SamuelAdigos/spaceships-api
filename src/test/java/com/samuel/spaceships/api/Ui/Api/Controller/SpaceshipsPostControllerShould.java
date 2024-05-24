package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Ui.Api.Controller.common.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

public class SpaceshipsPostControllerShould extends BaseControllerTest {

  @Test
  @WithMockUser(roles = "ADMIN")
  void create_a_valid_non_existing_spaceship_with_user_with_permissions() throws Exception {
    assertRequestWithBody(
        "POST",
        "/spaceships",
        "{\"name\": \"USS Enterprise\", \"franchise\": \"Star Trek\", \"maxSpeed\": 9.975}",
        201
    );
  }

  @Test
  @WithMockUser(roles = "USER")
  void create_a_valid_non_existing_spaceship_with_user_without_permissions() throws Exception {
    assertRequestWithBody(
        "POST",
        "/spaceships",
        "{\"name\": \"USS Enterprise\", \"franchise\": \"Star Trek\", \"maxSpeed\": 9.975}",
        403
    );
  }

  @Test
  void create_a_valid_non_existing_spaceship_with_no_user() throws Exception {
    assertRequestWithBody(
        "POST",
        "/spaceships",
        "{\"name\": \"USS Enterprise\", \"franchise\": \"Star Trek\", \"maxSpeed\": 9.975}",
        401
    );
  }
}
