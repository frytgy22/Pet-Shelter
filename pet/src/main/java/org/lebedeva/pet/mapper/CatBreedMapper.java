package org.lebedeva.pet.mapper;

import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
//(componentModel = "spring", uses = {CatMapper.class})
public interface CatBreedMapper extends EntityMapper<CatBreedDto, CatBreed> {
    @Mapping(target = "cats", ignore = true)
    CatBreed toEntity(CatBreedDto dto);

    @Mapping(target = "cats", ignore = true)
    CatBreedDto toDto(CatBreed entity);
}
