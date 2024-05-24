package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Update.UpdateSpaceshipCommand;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandBus;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandNotRegisteredError;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryBus;
import com.samuel.spaceships.api.Domain.DomainError;
import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Infrastructure.Spring.ApiController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceshipsPutController extends ApiController {

  public SpaceshipsPutController(QueryBus queryBus, CommandBus commandBus) {
    super(queryBus, commandBus);
  }

  @Override
  public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
    return new HashMap<>() {
      {
        put(SpaceshipNotExist.class, HttpStatus.NOT_FOUND);
      }
    };
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
  public ResponseEntity<String> index(
    @PathVariable String id,
    @RequestBody SpaceshipsDetailsRequestBody request
  ) throws CommandNotRegisteredError {
    dispatch(new UpdateSpaceshipCommand(id, request.getName(), request.getFranchise(), request.getMaxSpeed()));

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
