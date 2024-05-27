package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Delete.DeleteSpaceshipCommand;
import com.samuel.spaceships.api.Application.Delete.DeleteSpaceshipCommandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpaceshipsDeleteController {

  private final DeleteSpaceshipCommandHandler deleteSpaceshipCommandHandler;

  @Operation(summary = "Delete a spaceship")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "The spaceship was deleted"),
      @ApiResponse(responseCode = "404", description = "Spaceship not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "An internal server error occurred", content = @Content)})
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @DeleteMapping("/spaceships/{id}")
  public ResponseEntity<Void> index(@PathVariable String id) {
    DeleteSpaceshipCommand command = new DeleteSpaceshipCommand(id);
    deleteSpaceshipCommandHandler.execute(command);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}