package org.lebedeva.pet.mapper.animal.dog;

import org.lebedeva.pet.dto.dog.DogBreedDto;
import org.lebedeva.pet.model.animal.dog.DogBreed;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DogMapper.class})
public interface DogBreedMapper {

    DogBreed toEntity(DogBreedDto dto);

    DogBreedDto toDto(DogBreed entity);
}
