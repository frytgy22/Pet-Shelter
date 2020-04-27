package org.lebedeva.pet.service;

import org.lebedeva.pet.dto.dog.DogDto;
import org.lebedeva.pet.mapper.animal.dog.DogMapper;
import org.lebedeva.pet.model.animal.dog.Dog;
import org.lebedeva.pet.repository.DogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final DogMapper dogMapper;

    public DogServiceImpl(DogRepository dogRepository, DogMapper dogMapper) {
        this.dogRepository = dogRepository;
        this.dogMapper = dogMapper;
    }

    @Override
    public Dog save(DogDto dto) {
        Dog dog = dogMapper.toEntity(dto);
        return dogRepository.save(dog);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<DogDto> findAll(Pageable pageable) {
        return dogRepository.findAll(pageable)
                .map(dogMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<DogDto> findById(Integer id) {
        return dogRepository.findById(id)
                .map(dogMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        dogRepository.deleteById(id);
    }
}
