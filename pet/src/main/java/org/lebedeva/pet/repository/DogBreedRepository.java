package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.animal.dog.DogBreed;
import org.springframework.data.repository.CrudRepository;

public interface DogBreedRepository extends CrudRepository<DogBreed, Integer> {
}
