package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Ui.Api.Controller.common.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class SpaceshipsDeleteControllerShould extends BaseControllerTest {

  @MockBean
  private SpaceshipRepository spaceshipRepository;

  @Test
  @WithMockUser(roles = "ADMIN")
  void delete_an_existing_spaceship_with_user_with_permissions() throws Exception {
    final String UUID = "123e4567-e89b-12d3-a456-426614174000";
    givenDeleteDataBehaviourMocked();

    assertRequest(
        "DELETE",
        String.format("/spaceships/%s", UUID),
        204
    );
  }

  private void givenDeleteDataBehaviourMocked() {
    when(spaceshipRepository.existsById(any())).thenReturn(true);
    doNothing().when(spaceshipRepository).deleteById(any());
  }


}