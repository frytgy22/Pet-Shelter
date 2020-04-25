package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
