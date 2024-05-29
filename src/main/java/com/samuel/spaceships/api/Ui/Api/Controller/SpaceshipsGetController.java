package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Search.SearchSpaceshipsByCriteriaQuery;
import com.samuel.spaceships.api.Application.SpaceshipsResponse;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandBus;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryBus;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandlerExecutionError;
import com.samuel.spaceships.api.Domain.DomainError;
import com.samuel.spaceships.api.Infrastructure.Spring.ApiController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class SpaceshipsGetController extends ApiController {

  public SpaceshipsGetController(QueryBus queryBus, CommandBus commandBus) {
    super(queryBus, commandBus);
  }

  @Operation(summary = "Get a list of spaceships")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping("/spaceships")
  public List<HashMap<String, String>> index(@RequestParam HashMap<String, Serializable> params)
      throws QueryHandlerExecutionError {
    SpaceshipsResponse spaceships = ask(
        new SearchSpaceshipsByCriteriaQuery(
            parseFilters(params),
            Optional.ofNullable((String) params.get("order_by")),
            Optional.ofNullable((String) params.get("order")),
            Optional.ofNullable(
                params.get("limit") != null ? Integer.parseInt((String) params.get("limit"))
                    : null),
            Optional.ofNullable(
                params.get("offset") != null ? Integer.parseInt((String) params.get("offset"))
                    : null)
        )
    );

    return spaceships
        .spaceships()
        .stream()
        .map(response ->
            new HashMap<String, String>() {
              {
                put("id", response.getId());
                put("name", response.getName());
                put("franchise", response.getFranchise());
                put("maxSpeed", String.valueOf(response.getMaxSpeed()));
              }

            }

        )
        .collect(Collectors.toList());
  }

  private List<HashMap<String, String>> parseFilters(HashMap<String, Serializable> params) {
    int maxParams = params.size();

    List<HashMap<String, String>> filters = new ArrayList<>();

    for (int possibleFilterKey = 0; possibleFilterKey < maxParams; possibleFilterKey++) {
      if (params.containsKey(String.format("filters[%s][field]", possibleFilterKey))) {
        int key = possibleFilterKey;

        filters.add(
            new HashMap<>() {
              {
                put("field", (String) params.get(String.format("filters[%s][field]", key)));
                put("operator", (String) params.get(String.format("filters[%s][operator]", key)));
                put("value", (String) params.get(String.format("filters[%s][value]", key)));
              }
            }
        );
      }
    }
    return filters;
  }

  @Override
  public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
    return new HashMap<>() {
    };
  }
}
