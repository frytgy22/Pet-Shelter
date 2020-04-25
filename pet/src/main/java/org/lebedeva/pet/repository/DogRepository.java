package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.animal.dog.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Integer> {
}
