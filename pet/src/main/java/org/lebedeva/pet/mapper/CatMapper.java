//package org.lebedeva.pet.mapper;
//
//import org.lebedeva.pet.dto.cat.CatDto;
//import org.lebedeva.pet.model.animal.cat.Cat;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring", uses = {CatBreedMapper.class})
//public interface CatMapper extends EntityMapper<CatDto, Cat> {
//
//    @Mapping(source = "breedId", target = "breed.id")
//    Cat toEntity(CatDto dto);
//
//    @Mapping(source = "breed.id", target = "breedId")
//    @Mapping(source = "breed.name", target = "breedName")
//    CatDto toDto(Cat entity);
//}
