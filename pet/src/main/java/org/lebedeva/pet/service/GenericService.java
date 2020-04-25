package org.lebedeva.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenericService<D, E> {
    E save(D dto);

    Page<D> findAll(Pageable pageable);

    Optional<D> findById(Integer id) throws Exception;

    void delete(Integer id);
}
