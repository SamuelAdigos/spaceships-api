package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Delete.DeleteSpaceshipCommand;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandBus;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandNotRegisteredError;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryBus;
import com.samuel.spaceships.api.Domain.DomainError;
import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipWithInvalidUuidFormat;
import com.samuel.spaceships.api.Infrastructure.Spring.ApiController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceshipsDeleteController extends ApiController {

  public SpaceshipsDeleteController(QueryBus queryBus, CommandBus commandBus) {
    super(queryBus, commandBus);
  }

  @Operation(summary = "Delete a spaceship")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "The spaceship was deleted"),
      @ApiResponse(responseCode = "404", description = "Spaceship not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "An internal server error occurred", content = @Content)})
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @DeleteMapping("/spaceships/{id}")
  public ResponseEntity<Void> index(@PathVariable String id) throws CommandNotRegisteredError {
    dispatch(new DeleteSpaceshipCommand(id));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
    return new HashMap<>() {
      {
        put(SpaceshipWithInvalidUuidFormat.class, HttpStatus.BAD_REQUEST);
        put(SpaceshipNotExist.class, HttpStatus.NOT_FOUND);
      }
    };
  }
}