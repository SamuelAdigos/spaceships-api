package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Delete.DeleteSpaceshipCommand;
import com.samuel.spaceships.api.Application.Delete.DeleteSpaceshipCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpaceshipsDeleteControllerShould extends BaseControllerTest {

  @MockBean
  private DeleteSpaceshipCommandHandler deleteSpaceshipCommandHandler;

  @Test
  public void delete_a_spaceship() throws Exception {
    Mockito.doNothing().when(deleteSpaceshipCommandHandler).execute(any(DeleteSpaceshipCommand.class));

    mockMvc.perform(delete("/spaceships/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}