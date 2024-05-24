package com.samuel.spaceships.api.Infrastructure.Persistence;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipIdMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipNameMother;
import com.samuel.spaceships.api.Infrastructure.SpaceshipsModuleInfrastructureTestCase;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

public class MongoSpaceshipRepositoryShould extends SpaceshipsModuleInfrastructureTestCase {
  @Test
  void save_a_spaceship() {
    Spaceship spaceship = SpaceshipMother.random();

    mongoSpaceshipRepository.save(spaceship);
  }

  @Test
  void return_an_existing_spaceship() {
    Spaceship spaceship = SpaceshipMother.random();
    Spaceship savedSpaceship = mongoSpaceshipRepository.save(spaceship);

    Optional<Spaceship> found = mongoSpaceshipRepository.findById(savedSpaceship.id());

    assertThat(found).isEqualTo(Optional.of(spaceship));
  }

  @Test
  void return_all_spaceships() throws Exception {
    Spaceship spaceship1 = SpaceshipMother.random();
    Spaceship spaceship2 = SpaceshipMother.random();
    mongoSpaceshipRepository.save(spaceship1);
    mongoSpaceshipRepository.save(spaceship2);

    eventually(() -> {
      Page<Spaceship> found = mongoSpaceshipRepository.findAll(Pageable.unpaged());
      assertThat(found.getContent()).containsExactlyInAnyOrder(spaceship1, spaceship2);
    });
  }

  @ParameterizedTest
  @MethodSource("provideSpaceshipsForTest")
  void return_spaceships_by_name(Spaceship spaceship, String nameToSearch) throws Exception {
    mongoSpaceshipRepository.save(spaceship);

    eventually(() -> {
      Page<Spaceship> found = mongoSpaceshipRepository.findByNameContaining(nameToSearch, Pageable.unpaged());
      assertThat(found.getContent()).containsExactly(spaceship);
    });
  }

  @Test
  void delete_a_spaceship() {
    Spaceship spaceship = SpaceshipMother.random();
    mongoSpaceshipRepository.save(spaceship);

    mongoSpaceshipRepository.deleteById(spaceship.id());

    Optional<Spaceship> found = mongoSpaceshipRepository.findById(spaceship.id());
    assertThat(found).isEmpty();
  }

  @Test
  void return_spaceships_by_name() throws Exception {
    final String STARSHIP_NAME = "Enterprise";
    Spaceship spaceship = SpaceshipMother.randomBuilder().name(SpaceshipNameMother.create(STARSHIP_NAME)).build();
    mongoSpaceshipRepository.save(spaceship);

    eventually(() -> {
        Page<Spaceship> found = mongoSpaceshipRepository.findByNameContaining(STARSHIP_NAME, Pageable.unpaged());
        assertThat(found.getContent()).containsExactly(spaceship);
    });
  }

@Test
void check_spaceship_exists() throws Exception {
    Spaceship spaceship = SpaceshipMother.random();
    mongoSpaceshipRepository.save(spaceship);

    eventually(() -> {
        boolean exists = mongoSpaceshipRepository.existsById(spaceship.id());
        assertThat(exists).isTrue();
    });
}

  private static Stream<Arguments> provideSpaceshipsForTest() {
    return Stream.of(
        Arguments.of(SpaceshipMother.randomBuilder().name(SpaceshipNameMother.create("Enterprise")).build(), "Enterprise"),
        Arguments.of(SpaceshipMother.randomBuilder().name(SpaceshipNameMother.create("Millenium Falcon")).build(), "Falcon"),
        Arguments.of(SpaceshipMother.randomBuilder().name(SpaceshipNameMother.create("Serenity")), "Serenity")
    );
  }
}
