package org.lebedeva.pet.mapper;

import org.lebedeva.pet.dto.cat.CatDto;
import org.lebedeva.pet.model.animal.Gender;
import org.lebedeva.pet.model.animal.cat.Cat;
import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CatBreedMapper.class})
public interface CatMapper {

    default Cat toEntity(CatDto dto) throws EnumConstantNotPresentException {
        return new Cat(dto.getId(), new CatBreed(dto.getBreedId(),"test",null),
                dto.getBirthDate(), dto.getPhoto(), Gender.valueOf(dto.getGender()), dto.getDescription());
    }

    default CatDto toDto(Cat entity) {
        return new CatDto(entity.getId(), entity.getBreed().getId(), entity.getBreed().getName(),
                entity.getBirthDate(), entity.getPhoto(), entity.getGender().toString(), entity.getDescription());
    }
}
