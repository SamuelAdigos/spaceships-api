package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Update.UpdateSpaceshipCommand;
import com.samuel.spaceships.api.Application.Update.UpdateSpaceshipCommandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.Serializable;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceshipsPutController {
  private final UpdateSpaceshipCommandHandler commandHandler;

  public SpaceshipsPutController(UpdateSpaceshipCommandHandler commandHandler) {
    this.commandHandler = commandHandler;
  }

  @Operation(summary = "Update an existing spaceship")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Spaceship updated successfully", content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "404", description = "Spaceship not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PutMapping(value = "/spaceships/{id}")
  public ResponseEntity<HashMap<String, Serializable>> update(
    @PathVariable Long id,
    @RequestBody SpaceshipsPutControllerRequestBody body
  ) {
    var command = new UpdateSpaceshipCommand(id, body.name(), body.franchise(), body.maxSpeed());
    commandHandler.execute(command);

    return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>());
  }
}
