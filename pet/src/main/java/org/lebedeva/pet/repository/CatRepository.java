package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.animal.cat.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, Integer> {
}
