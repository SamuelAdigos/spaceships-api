package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Create.CreateSpaceshipCommand;
import com.samuel.spaceships.api.Application.Create.CreateSpaceshipCommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipDto;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceshipsPostController {
  private final CreateSpaceshipCommandHandler commandHandler;
  @Qualifier("dtoSpaceshipMapper") private final SpaceshipMapper spaceshipMapper;

  public SpaceshipsPostController(
      CreateSpaceshipCommandHandler commandHandler,
      SpaceshipMapper spaceshipMapper
  ) {
    this.commandHandler = commandHandler;
    this.spaceshipMapper = spaceshipMapper;
  }

  @Operation(summary = "Create a new spaceship")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Spaceship created successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceshipDto.class))
      }),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/spaceships")
  public ResponseEntity<SpaceshipDto> create(
      @RequestBody SpaceshipsPostControllerRequestBody body
  ) {
    var command = new CreateSpaceshipCommand(body.name(), body.franchise(), body.maxSpeed());
    Spaceship spaceship = commandHandler.execute(command);
    SpaceshipDto spaceshipDto = spaceshipMapper.toDto(spaceship);
    return ResponseEntity.status(HttpStatus.CREATED).body(spaceshipDto);
  }
}
