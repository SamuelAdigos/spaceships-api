package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Get.*;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipDto;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpaceshipsGetControllerShould extends BaseControllerTest {

  @MockBean
  private GetAllSpaceshipsCommandHandler getAllSpaceshipsCommandHandler;

  @MockBean
  private GetSpaceshipByIdCommandHandler getSpaceshipByIdCommandHandler;

  @MockBean
  private GetSpaceshipsByNameCommandHandler getSpaceshipsByNameCommandHandler;

  @MockBean
  private SpaceshipMapper spaceshipMapper;

  @MockBean
  private PagedResourcesAssembler<SpaceshipDto> pagedResourcesAssembler;

  @Test
  public void get_all_spaceships() throws Exception {
    Pageable pageable = PageRequest.of(0, 10);
    Spaceship spaceship = SpaceshipMother.withDefaults();
    PageImpl<Spaceship> spaceshipPage = new PageImpl<>(Collections.singletonList(spaceship), pageable, 1);
    SpaceshipDto spaceshipDto = new SpaceshipDto(spaceship.id(), spaceship.name(), spaceship.franchise(), spaceship.maxSpeed());

    Mockito.when(getAllSpaceshipsCommandHandler.execute(any(GetAllSpaceshipsCommand.class)))
        .thenReturn(spaceshipPage);
    Mockito.when(spaceshipMapper.toDto(any(Spaceship.class)))
        .thenReturn(spaceshipDto);

    mockMvc.perform(get("/spaceships").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void get_spaceship_by_id() throws Exception {
    Spaceship spaceship = SpaceshipMother.withDefaults();
    SpaceshipDto spaceshipDto = new SpaceshipDto(spaceship.id(), spaceship.name(), spaceship.franchise(), spaceship.maxSpeed());

    Mockito.when(getSpaceshipByIdCommandHandler.execute(any(GetSpaceshipByIdCommand.class)))
        .thenReturn(spaceship);
    Mockito.when(spaceshipMapper.toDto(any(Spaceship.class)))
        .thenReturn(spaceshipDto);

    mockMvc.perform(get("/spaceships/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void get_spaceship_by_name() throws Exception {
    Pageable pageable = PageRequest.of(0, 10);
    Spaceship spaceship = SpaceshipMother.withDefaults();
    PageImpl<Spaceship> spaceshipPage = new PageImpl<>(Collections.singletonList(spaceship), pageable, 1);
    SpaceshipDto spaceshipDto = new SpaceshipDto(spaceship.id(), spaceship.name(), spaceship.franchise(), spaceship.maxSpeed());

    Mockito.when(getSpaceshipsByNameCommandHandler.execute(any(GetSpaceshipsByNameCommand.class)))
        .thenReturn(spaceshipPage);
    Mockito.when(spaceshipMapper.toDto(any(Spaceship.class)))
        .thenReturn(spaceshipDto);

    mockMvc.perform(get("/spaceships?name=test").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
