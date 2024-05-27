package com.samuel.spaceships.api.Infrastructure.Persistence.Mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "spaceships")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpaceshipData {

  @Id
  private String id;

  private String name;

  private String franchise;

  private double maxSpeed;
}