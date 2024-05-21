package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Create.CreateSpaceshipCommand;
import com.samuel.spaceships.api.Application.Create.CreateSpaceshipCommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipDto;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpaceshipsPostControllerShould extends BaseControllerTest {

  @MockBean
  private CreateSpaceshipCommandHandler commandHandler;

  @MockBean
  private SpaceshipMapper spaceshipMapper;

  @Test
  public void create_a_spaceship() throws Exception {
    Spaceship spaceship = SpaceshipMother.withDefaults();
    SpaceshipDto spaceshipDto = new SpaceshipDto(spaceship.id(), spaceship.name(), spaceship.franchise(), spaceship.maxSpeed());

    Mockito.when(commandHandler.execute(any(CreateSpaceshipCommand.class)))
        .thenReturn(spaceship);
    Mockito.when(spaceshipMapper.toDto(any(Spaceship.class)))
        .thenReturn(spaceshipDto);

    String requestBody = """
                {
                    "name": "USS Enterprise",
                    "franchise": "Star Trek",
                    "maxSpeed": 9.975
                }
                """;

    mockMvc.perform(post("/spaceships")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    ArgumentCaptor<CreateSpaceshipCommand> commandCaptor = ArgumentCaptor.forClass(CreateSpaceshipCommand.class);
    Mockito.verify(commandHandler).execute(commandCaptor.capture());
    CreateSpaceshipCommand capturedCommand = commandCaptor.getValue();

    assertThat(capturedCommand.name()).isEqualTo("USS Enterprise");
    assertThat(capturedCommand.franchise()).isEqualTo("Star Trek");
    assertThat(capturedCommand.maxSpeed()).isEqualTo(9.975);
  }
}
