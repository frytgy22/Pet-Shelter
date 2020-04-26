package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
