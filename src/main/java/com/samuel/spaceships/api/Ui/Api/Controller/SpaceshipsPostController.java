package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Create.CreateSpaceshipCommand;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandBus;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandNotRegisteredError;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryBus;
import com.samuel.spaceships.api.Domain.DomainError;
import com.samuel.spaceships.api.Infrastructure.Spring.ApiController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceshipsPostController extends ApiController {

  public SpaceshipsPostController(QueryBus queryBus, CommandBus commandBus) {
    super(queryBus, commandBus);
  }

  @Operation(summary = "Create a new spaceship")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Spaceship created successfully", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
      }),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/spaceships")
  public ResponseEntity<String> index(
      @RequestBody SpaceshipsDetailsRequestBody request
  ) throws CommandNotRegisteredError {
    dispatch(new CreateSpaceshipCommand(request.getName(), request.getFranchise(),
        request.getMaxSpeed()));

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
    return new HashMap<>() {
    };
  }
}
