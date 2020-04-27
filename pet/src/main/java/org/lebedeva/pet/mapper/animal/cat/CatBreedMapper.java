package org.lebedeva.pet.mapper.animal.cat;

import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {CatMapper.class})
public interface CatBreedMapper {

    CatBreed toEntity(CatBreedDto dto);

    CatBreedDto toDto(CatBreed entity);
}
