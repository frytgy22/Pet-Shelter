package org.lebedeva.pet.mapper.animal.dog;

import org.lebedeva.pet.dto.dog.DogDto;
import org.lebedeva.pet.model.animal.Gender;
import org.lebedeva.pet.model.animal.dog.Dog;
import org.lebedeva.pet.model.animal.dog.DogBreed;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DogBreedMapper.class})
public interface DogMapper {

    default Dog toEntity(DogDto dto) throws EnumConstantNotPresentException {
        return new Dog(dto.getId(), new DogBreed(dto.getBreedId(), "", null),
                dto.getBirthDate(), dto.getPhoto(), Gender.valueOf(dto.getGender()), dto.getDescription());
    }

    default DogDto toDto(Dog entity) {
        return new DogDto(entity.getId(), entity.getBreed().getId(), entity.getBreed().getName(),
                entity.getBirthDate(), entity.getPhoto(), entity.getGender().toString(), entity.getDescription());
    }
}
