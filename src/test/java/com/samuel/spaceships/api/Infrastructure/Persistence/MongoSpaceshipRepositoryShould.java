package com.samuel.spaceships.api.Infrastructure.Persistence;

import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import com.samuel.spaceships.api.Domain.Spaceship.Spaceship;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipCriteriaMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipMother;
import com.samuel.spaceships.api.Domain.Spaceship.SpaceshipNameMother;
import com.samuel.spaceships.api.Infrastructure.SpaceshipsModuleInfrastructureTestCase;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
    mongoSpaceshipRepository.save(spaceship);

    Criteria criteria = SpaceshipCriteriaMother.nameContains(spaceship.name().value());
    List<Spaceship> found = mongoSpaceshipRepository.matching(criteria);

    assertThat(found).contains(spaceship);
  }

  @Test
  void return_all_spaceships_with_no_criteria() throws Exception {
    Spaceship spaceship1 = SpaceshipMother.random();
    Spaceship spaceship2 = SpaceshipMother.random();
    mongoSpaceshipRepository.save(spaceship1);
    mongoSpaceshipRepository.save(spaceship2);

    eventually(() -> {
      Criteria criteria = SpaceshipCriteriaMother.emptyCriteria();
      List<Spaceship> found = mongoSpaceshipRepository.matching(criteria);
      assertThat(found).containsExactlyInAnyOrder(spaceship1, spaceship2);
    });
  }

  @ParameterizedTest
  @MethodSource("provideSpaceshipsForTest")
  void return_spaceships_by_name(Spaceship spaceship, String nameToSearch) throws Exception {
    mongoSpaceshipRepository.save(spaceship);

    eventually(() -> {
      Criteria criteria = SpaceshipCriteriaMother.nameContains(nameToSearch);
      List<Spaceship> found = mongoSpaceshipRepository.matching(criteria);
      assertThat(found).containsExactly(spaceship);
    });
  }

  @Test
  void delete_a_spaceship() {
    Spaceship spaceship = SpaceshipMother.random();
    mongoSpaceshipRepository.save(spaceship);

    mongoSpaceshipRepository.deleteById(spaceship.id());

    Boolean found = mongoSpaceshipRepository.existsById(spaceship.id());
    assertThat(found).isFalse();
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
        Arguments.of(
            SpaceshipMother.randomBuilder().name(SpaceshipNameMother.create("Enterprise")).build(),
            "Enterprise"),
        Arguments.of(
            SpaceshipMother.randomBuilder().name(SpaceshipNameMother.create("Millenium Falcon"))
                .build(), "Falcon")
    );
  }
}
