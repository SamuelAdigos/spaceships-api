package com.samuel.spaceships.api.Ui.Api.Controller;

import com.samuel.spaceships.api.Application.Get.SpaceshipGetter;
import com.samuel.spaceships.api.Application.SpaceshipResponse;
import com.samuel.spaceships.api.Application.SpaceshipsPageResponse;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Ui.Api.Controller.common.BaseControllerTest;
import com.samuel.spaceships.api.Ui.Api.Controller.common.TestDataUtil;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SpaceshipsGetControllerShould extends BaseControllerTest {

  @MockBean
  SpaceshipGetter spaceshipGetter;

  @Test
  @WithMockUser(roles = "USER")
  public void shouldReturnAllSpaceships() throws Exception {
    List<Spaceship> spaceshipList = TestDataUtil.getSpaceships();
    mockSpaceshipsData(spaceshipList);

    assertResponse(
        "/spaceships",
        200,
        getExpectedResponse(
            "SpaceshipsGetController/GetAllSpaceships/expected_response.json"));
  }

  @Test
  @WithMockUser(roles = "USER")
  public void shouldReturnSpaceshipById() throws Exception {
    val uuidToFind = "123e4567-e89b-12d3-a456-426614174000";
    Spaceship spaceship = TestDataUtil.getSpaceshipById(uuidToFind);
    mockSpaceshipByIdData(spaceship);

    assertResponse(
        "/spaceships/" + uuidToFind,
        200,
        getExpectedResponse(
            "SpaceshipsGetController/GetSpaceshipById/expected_response.json"));
  }

  @Test
  @WithMockUser(roles = "USER")
  public void shouldReturnSpaceshipByName() throws Exception {
    val nameToFind = "star";
    List<Spaceship> spaceshipList = TestDataUtil.getSpaceshipsByName(nameToFind);
    mockSpaceshipByNameData(spaceshipList);

    assertResponse(
        "/spaceships?name=" + nameToFind,
        200,
        getExpectedResponse(
            "SpaceshipsGetController/GetSpaceshipByName/expected_response.json"));
  }

  private void mockSpaceshipsData(List<Spaceship> spaceshipList) {
    Page<Spaceship> spaceshipsPage = new PageImpl<>(spaceshipList);
    SpaceshipsPageResponse response = SpaceshipsPageResponse.fromPage(spaceshipsPage);
    when(spaceshipGetter.getAllSpaceships(any())).thenReturn(response);
  }

  private void mockSpaceshipByIdData(Spaceship spaceship) {
    when(spaceshipGetter.getSpaceshipById(any())).thenReturn(
        SpaceshipResponse.fromAggregate(spaceship));
  }

  private void mockSpaceshipByNameData(List<Spaceship> spaceshipList) {
    Page<Spaceship> spaceshipsPage = new PageImpl<>(spaceshipList);
    SpaceshipsPageResponse response = SpaceshipsPageResponse.fromPage(spaceshipsPage);
    when(spaceshipGetter.getSpaceshipsByName(anyString(), any())).thenReturn(response);
  }
}