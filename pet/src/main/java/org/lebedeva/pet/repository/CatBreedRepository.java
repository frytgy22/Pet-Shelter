package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CatBreedRepository extends JpaRepository<CatBreed, Integer> {

    @Query("select breed from CatBreed breed left join fetch breed.cats where breed.id =:id")
    Optional<CatBreed> findOneWithEagerRelationships(@Param("id") Integer id);
}
