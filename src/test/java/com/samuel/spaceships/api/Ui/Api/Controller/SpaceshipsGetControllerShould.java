package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Search.SpaceshipsByCriteriaSearcher;
import com.samuel.spaceships.api.Application.SpaceshipResponse;
import com.samuel.spaceships.api.Application.SpaceshipsResponse;
import com.samuel.spaceships.api.Ui.Api.Controller.common.BaseControllerTest;
import com.samuel.spaceships.api.Ui.Api.Controller.common.TestDataUtil;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SpaceshipsGetControllerShould extends BaseControllerTest {

  @MockBean
  SpaceshipsByCriteriaSearcher spaceshipsByCriteriaSearcher;

  @Test
  @WithMockUser(roles = "USER")
  public void get_all_spaceships_with_user_with_permissions() throws Exception {
    List<SpaceshipResponse> spaceshipResponseList = TestDataUtil.getSpaceships().stream()
        .map(SpaceshipResponse::fromAggregate)
        .toList();
    mockWhenGetAllSpaceshipsData(spaceshipResponseList);

    assertResponse(
        "/spaceships",
        200,
        getExpectedResponse(
            "SpaceshipsGetController/GetAllSpaceships/expected_response.json"));
  }

  @Test
  @WithMockUser(roles = "USER")
  public void get_spaceship_by_id_with_user_with_permissions() throws Exception {
    val uuidToFind = "123e4567-e89b-12d3-a456-426614174000";
    List<SpaceshipResponse> spaceshipResponseList = List.of(
        SpaceshipResponse.fromAggregate(TestDataUtil.getSpaceshipById(uuidToFind)));
    mockWhenGetSpaceshipById(spaceshipResponseList);

    assertResponse(
        "/spaceships?filters%5B0%5D%5Bvalue%5D=" + uuidToFind
            + "&filters%5B0%5D%5Bfield%5D=id&filters%5B0%5D%5Boperator%5D=EQUAL",
        200,
        getExpectedResponse(
            "SpaceshipsGetController/GetSpaceshipById/expected_response.json"));
  }

  @Test
  @WithMockUser(roles = "USER")
  public void get_spaceships_by_name_with_user_with_permissions() throws Exception {
    val nameToFind = "star";
    List<SpaceshipResponse> spaceshipResponseList = TestDataUtil.getSpaceshipsByName(nameToFind)
        .stream()
        .map(SpaceshipResponse::fromAggregate)
        .toList();
    mockWhenGetSpaceshipByName(spaceshipResponseList);

    assertResponse(
        "/spaceships?filters%5B0%5D%5Bvalue%5D=" + nameToFind
            + "&filters%5B0%5D%5Bfield%5D=name&filters%5B0%5D%5Boperator%5D=CONTAINS",
        200,
        getExpectedResponse(
            "SpaceshipsGetController/GetSpaceshipByName/expected_response.json"));
  }

  private void mockWhenGetAllSpaceshipsData(List<SpaceshipResponse> spaceshipList) {
    SpaceshipsResponse spaceshipsPageResponse = new SpaceshipsResponse(spaceshipList);
    when(spaceshipsByCriteriaSearcher.search(any(), any(), any(), any())).thenReturn(
        spaceshipsPageResponse);
  }

  private void mockWhenGetSpaceshipById(List<SpaceshipResponse> spaceshipList) {
    SpaceshipsResponse spaceshipsPageResponse = new SpaceshipsResponse(spaceshipList);
    when(spaceshipsByCriteriaSearcher.search(any(), any(), any(),
        any())).thenReturn(
        spaceshipsPageResponse);
  }

  private void mockWhenGetSpaceshipByName(List<SpaceshipResponse> spaceshipList) {
    SpaceshipsResponse spaceshipsPageResponse = new SpaceshipsResponse(spaceshipList);
    when(spaceshipsByCriteriaSearcher.search(any(), any(), any(), any())).thenReturn(
        spaceshipsPageResponse);
  }
}