package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Get.GetAllSpaceshipsQuery;
import com.samuel.spaceships.api.Application.Get.GetSpaceshipByIdQuery;
import com.samuel.spaceships.api.Application.Get.GetSpaceshipsByNameQuery;
import com.samuel.spaceships.api.Application.SpaceshipResponse;
import com.samuel.spaceships.api.Application.SpaceshipsPageResponse;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandBus;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryBus;
import com.samuel.spaceships.api.Domain.DomainError;
import com.samuel.spaceships.api.Domain.Spaceship.Errors.SpaceshipNotExist;
import com.samuel.spaceships.api.Infrastructure.Spring.ApiController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpaceshipsGetController extends ApiController {

  public SpaceshipsGetController(QueryBus queryBus, CommandBus commandBus) {
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

  @Operation(summary = "Get all spaceships")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of spaceships"),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping("/spaceships")
  public ResponseEntity<Page<SpaceshipResponse>> getAllSpaceships(@ParameterObject Pageable pageable) {
    SpaceshipsPageResponse response = ask(new GetAllSpaceshipsQuery(pageable));
    return ResponseEntity.ok(response.getSpaceships());
  }

  @Operation(summary = "Get spaceship by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved spaceship"),
      @ApiResponse(responseCode = "404", description = "Spaceship not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping(value = "/spaceships/{id}")
  public ResponseEntity<SpaceshipResponse> getSpaceshipById(@PathVariable String id) {
    SpaceshipResponse response = ask(new GetSpaceshipByIdQuery(id));
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Get spaceships by name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of spaceships by name"),
      @ApiResponse(responseCode = "404", description = "Spaceships not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping(value = "/spaceships", params = "name")
  public ResponseEntity<Page<SpaceshipResponse>> getSpaceshipsByName(@RequestParam String name, @ParameterObject Pageable pageable) {
    SpaceshipsPageResponse response = ask(new GetSpaceshipsByNameQuery(name, pageable));
    return ResponseEntity.ok(response.getSpaceships());
  }
}
