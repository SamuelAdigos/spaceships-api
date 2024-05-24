package com.samuel.spaceships.api.Ui.Api.Controller.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Infrastructure.Persistence.Mapper.SpaceshipMapper;
import com.samuel.spaceships.api.Infrastructure.Persistence.Mongo.SpaceshipData;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;

public class TestDataUtil {

  public static List<Spaceship> getSpaceships() throws Exception {
    return getSpaceshipDataList().stream()
        .map(new SpaceshipMapper()::toEntity)
        .collect(Collectors.toList());
  }

  public static Spaceship getSpaceshipById(String id) throws Exception {
    return getSpaceshipDataList().stream()
        .map(new SpaceshipMapper()::toEntity)
        .filter(spaceship -> spaceship.id().value().equals(id))
        .findFirst()
        .orElseThrow();
  }

  public static List<Spaceship> getSpaceshipsByName(String name) throws Exception {
    return getSpaceshipDataList().stream()
        .filter(data -> data.getName().toLowerCase().contains(name))
        .map(new SpaceshipMapper()::toEntity)
        .collect(Collectors.toList());
  }

  private static List<SpaceshipData> getSpaceshipDataList() throws Exception {
    InputStream inputStream = new ClassPathResource(
        "SpaceshipsGetController/spaceships.json").getInputStream();

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(inputStream, new TypeReference<>() {
    });
  }
}
