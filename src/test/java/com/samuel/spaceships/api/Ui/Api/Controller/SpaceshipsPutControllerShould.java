package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Update.UpdateSpaceshipCommand;
import com.samuel.spaceships.api.Application.Update.UpdateSpaceshipCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpaceshipsPutControllerShould extends BaseControllerTest {

  @MockBean
  private UpdateSpaceshipCommandHandler commandHandler;

  @Test
  public void update_a_spaceship() throws Exception {
    Mockito.doNothing().when(commandHandler).execute(any(UpdateSpaceshipCommand.class));

    String requestBody = """
                {
                    "name": "USS Enterprise",
                    "franchise": "Star Trek",
                    "maxSpeed": 9.975
                }
                """;

    mockMvc.perform(put("/spaceships/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().json("{}"));

    ArgumentCaptor<UpdateSpaceshipCommand> commandCaptor = ArgumentCaptor.forClass(UpdateSpaceshipCommand.class);
    Mockito.verify(commandHandler).execute(commandCaptor.capture());
    UpdateSpaceshipCommand capturedCommand = commandCaptor.getValue();

    assertThat(capturedCommand.id()).isEqualTo(1L);
    assertThat(capturedCommand.name()).isEqualTo("USS Enterprise");
    assertThat(capturedCommand.franchise()).isEqualTo("Star Trek");
    assertThat(capturedCommand.maxSpeed()).isEqualTo(9.975);
  }
}
