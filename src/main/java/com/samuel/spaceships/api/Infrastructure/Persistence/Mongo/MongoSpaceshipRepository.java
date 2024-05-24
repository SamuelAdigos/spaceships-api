package com.samuel.spaceships.api.Infrastructure.Persistence.Mongo;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.Repository.SpaceshipRepository;
import com.samuel.spaceships.api.Infrastructure.Persistence.Mapper.SpaceshipMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MongoSpaceshipRepository implements SpaceshipRepository {
  private final BaseSpaceshipMongoRepository repository;
  private final SpaceshipMapper mapper;

  @Override
  public Spaceship save(Spaceship spaceship) {
    SpaceshipData entity = mapper.toData(spaceship);
    SpaceshipData savedEntity = repository.save(entity);
    return mapper.toEntity(savedEntity);
  }

  @Override
  public Optional<Spaceship> findById(SpaceshipId id) {
    return repository.findById(id.value()).map(mapper::toEntity);
  }

  @Override
  public Page<Spaceship> findAll(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toEntity);
  }

  @Override
  public Page<Spaceship> findByNameContaining(String name, Pageable pageable) {
    Page<SpaceshipData> page = repository.findByNameContaining(name, pageable);
    List<Spaceship> spaceships = page.getContent().stream()
        .map(mapper::toEntity)
        .collect(Collectors.toList());
    return new PageImpl<>(spaceships, pageable, page.getTotalElements());
  }

  @Override
  public void deleteById(SpaceshipId id) {
    repository.deleteById(id.value());
  }

  @Override
  public boolean existsById(SpaceshipId id) {
    return repository.existsById(id.value());
  }
}