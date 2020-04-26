package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.animal.dog.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Integer> {
}
