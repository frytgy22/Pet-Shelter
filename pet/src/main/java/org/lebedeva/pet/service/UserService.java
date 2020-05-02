package org.lebedeva.pet.service;

import org.lebedeva.pet.dto.user.UserDto;
import org.lebedeva.pet.model.user.User;

import java.util.Optional;

public interface UserService extends GenericService<UserDto, User> {
    Optional<User> findByEmail(String email);
}
