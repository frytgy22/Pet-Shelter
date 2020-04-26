package org.lebedeva.pet.service;

import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.model.animal.cat.CatBreed;

import java.util.List;

public interface CatBreedService extends GenericService<CatBreedDto, CatBreed> {
    List<CatBreedDto> findCatBreedsDto();
}
