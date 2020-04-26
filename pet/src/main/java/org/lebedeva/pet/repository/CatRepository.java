package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.animal.cat.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}
