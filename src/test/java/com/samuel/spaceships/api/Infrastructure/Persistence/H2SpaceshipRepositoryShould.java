package com.samuel.spaceships.api.Infrastructure.Persistence;

import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipId;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
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

public class H2SpaceshipRepositoryShould extends SpaceshipsModuleInfrastructureTestCase {
  @Test
  void save_a_spaceship() {
    Spaceship spaceship = SpaceshipMother.withDefaults();

    h2SpaceshipRepository.save(spaceship);
  }

  @Test
  void return_an_existing_spaceship() {
    Spaceship spaceship = SpaceshipMother.builder().withId(1L).build();
    Spaceship savedSpaceship = h2SpaceshipRepository.save(spaceship);

    Optional<Spaceship> found = h2SpaceshipRepository.findById(new SpaceshipId(savedSpaceship.id()));

    assertThat(found).isEqualTo(Optional.of(spaceship));
  }

  @Test
  void return_all_spaceships() throws Exception {
    Spaceship spaceship1 = SpaceshipMother.builder().withId(1L).build();
    Spaceship spaceship2 = SpaceshipMother.builder().withId(2L).build();
    h2SpaceshipRepository.save(spaceship1);
    h2SpaceshipRepository.save(spaceship2);

    eventually(() -> {
      Page<Spaceship> found = h2SpaceshipRepository.findAll(Pageable.unpaged());
      assertThat(found.getContent()).containsExactlyInAnyOrder(spaceship1, spaceship2);
    });
  }

  @ParameterizedTest
  @MethodSource("provideSpaceshipsForTest")
  void return_spaceships_by_name(Spaceship spaceship, String nameToSearch) throws Exception {
    h2SpaceshipRepository.save(spaceship);

    eventually(() -> {
      Page<Spaceship> found = h2SpaceshipRepository.findByNameContaining(nameToSearch, Pageable.unpaged());
      assertThat(found.getContent()).containsExactly(spaceship);
    });
  }

  @Test
  void delete_a_spaceship() {
    Spaceship spaceship = SpaceshipMother.builder().withId(1L).build();
    h2SpaceshipRepository.save(spaceship);

    h2SpaceshipRepository.deleteById(new SpaceshipId(spaceship.id()));

    Optional<Spaceship> found = h2SpaceshipRepository.findById(new SpaceshipId(spaceship.id()));
    assertThat(found).isEmpty();
  }

  @Test
  void return_spaceships_by_name() throws Exception {
    Spaceship spaceship = SpaceshipMother.builder().withName("Enterprise").build();
    h2SpaceshipRepository.save(spaceship);

    eventually(() -> {
        Page<Spaceship> found = h2SpaceshipRepository.findByNameContaining("Enterprise", Pageable.unpaged());
        assertThat(found.getContent()).containsExactly(spaceship);
    });
  }

@Test
void check_spaceship_exists() throws Exception {
    Spaceship spaceship = SpaceshipMother.builder().withId(1L).build();
    h2SpaceshipRepository.save(spaceship);

    eventually(() -> {
        boolean exists = h2SpaceshipRepository.existsById(new SpaceshipId(spaceship.id()));
        assertThat(exists).isTrue();
    });
}

  private static Stream<Arguments> provideSpaceshipsForTest() {
    return Stream.of(
        Arguments.of(SpaceshipMother.builder().withName("Enterprise").build(), "Enterprise"),
        Arguments.of(SpaceshipMother.builder().withName("Millennium Falcon").build(), "Falcon"),
        Arguments.of(SpaceshipMother.builder().withName("Serenity").build(), "Serenity")
    );
  }
}
