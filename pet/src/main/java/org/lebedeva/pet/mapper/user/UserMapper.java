package org.lebedeva.pet.mapper.user;

import org.lebedeva.pet.dto.user.UserDto;
import org.lebedeva.pet.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);

    @Mapping(source = "password", target = "confirmPassword")
    UserDto toDto(User entity);
}
