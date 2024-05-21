package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Get.GetAllSpaceshipsCommand;
import com.samuel.spaceships.api.Application.Get.GetAllSpaceshipsCommandHandler;
import com.samuel.spaceships.api.Application.Get.GetSpaceshipByIdCommand;
import com.samuel.spaceships.api.Application.Get.GetSpaceshipByIdCommandHandler;
import com.samuel.spaceships.api.Application.Get.GetSpaceshipsByNameCommand;
import com.samuel.spaceships.api.Application.Get.GetSpaceshipsByNameCommandHandler;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipDto;
import com.samuel.spaceships.api.Ui.Api.Dto.SpaceshipMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceshipsGetController {
  private final GetAllSpaceshipsCommandHandler getAllSpaceshipsCommandHandler;
  private final GetSpaceshipByIdCommandHandler getSpaceshipByIdCommandHandler;
  private final GetSpaceshipsByNameCommandHandler getSpaceshipsByNameCommandHandler;
  @Qualifier("dtoSpaceshipMapper") private final SpaceshipMapper spaceshipMapper;

  public SpaceshipsGetController(GetAllSpaceshipsCommandHandler getAllSpaceshipsCommandHandler, GetSpaceshipByIdCommandHandler getSpaceshipByIdCommandHandler, GetSpaceshipsByNameCommandHandler getSpaceshipsByNameCommandHandler, SpaceshipMapper spaceshipMapper) {
    this.getAllSpaceshipsCommandHandler = getAllSpaceshipsCommandHandler;
    this.getSpaceshipByIdCommandHandler = getSpaceshipByIdCommandHandler;
    this.getSpaceshipsByNameCommandHandler = getSpaceshipsByNameCommandHandler;
    this.spaceshipMapper = spaceshipMapper;
  }

  @Operation(summary = "Get all spaceships")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of spaceships"),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping(value = "/spaceships")
  public ResponseEntity<PagedModel<EntityModel<SpaceshipDto>>> getAllSpaceships(@ParameterObject Pageable pageable, PagedResourcesAssembler<SpaceshipDto> assembler) {
    GetAllSpaceshipsCommand command = new GetAllSpaceshipsCommand(pageable);
    Page<Spaceship> spaceships = getAllSpaceshipsCommandHandler.execute(command);
    Page<SpaceshipDto> spaceshipDtos = spaceships.map(spaceshipMapper::toDto);
    return ResponseEntity.ok(assembler.toModel(spaceshipDtos));
  }

  @Operation(summary = "Get spaceship by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved spaceship"),
      @ApiResponse(responseCode = "404", description = "Spaceship not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping(value = "/spaceships/{id}")
  public ResponseEntity<SpaceshipDto> getSpaceshipById(@PathVariable Long id) {
    GetSpaceshipByIdCommand command = new GetSpaceshipByIdCommand(id);
    Spaceship spaceship = getSpaceshipByIdCommandHandler.execute(command);
    SpaceshipDto spaceshipDto = spaceshipMapper.toDto(spaceship);
    return ResponseEntity.ok(spaceshipDto);
  }

  @Operation(summary = "Get spaceships by name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of spaceships by name"),
      @ApiResponse(responseCode = "404", description = "Spaceships not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping(value = "/spaceships", params = "name")
  public ResponseEntity<PagedModel<EntityModel<SpaceshipDto>>> getSpaceshipsByName(@RequestParam String name, @ParameterObject Pageable pageable, PagedResourcesAssembler<SpaceshipDto> assembler) {
    GetSpaceshipsByNameCommand command = new GetSpaceshipsByNameCommand(name, pageable);
    Page<Spaceship> spaceships = getSpaceshipsByNameCommandHandler.execute(command);
    Page<SpaceshipDto> spaceshipDtos = spaceships.map(spaceshipMapper::toDto);
    return ResponseEntity.ok(assembler.toModel(spaceshipDtos));
  }

}
