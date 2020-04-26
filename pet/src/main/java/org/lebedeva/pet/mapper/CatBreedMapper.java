package org.lebedeva.pet.mapper;

import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {CatMapper.class})
public interface CatBreedMapper {
   // @Mapping(target = "cats", ignore = true)

    CatBreed toEntity(CatBreedDto dto);

   // @Mapping(target = "cats", ignore = true)
   //@InheritInverseConfiguration
    CatBreedDto toDto(CatBreed entity);
}
