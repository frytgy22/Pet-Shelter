package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.animal.dog.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogBreedRepository extends JpaRepository<DogBreed, Integer> {
}
