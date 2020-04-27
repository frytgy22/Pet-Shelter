package org.lebedeva.pet.service;

import org.lebedeva.pet.dto.dog.DogBreedDto;
import org.lebedeva.pet.model.animal.dog.DogBreed;

import java.util.List;

public interface DogBreedService extends GenericService<DogBreedDto, DogBreed> {
    List<DogBreedDto> findDogBreedsDto();
}
