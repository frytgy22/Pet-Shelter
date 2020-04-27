package org.lebedeva.pet.service.impl;

import org.lebedeva.pet.dto.dog.DogBreedDto;
import org.lebedeva.pet.mapper.animal.dog.DogBreedMapper;
import org.lebedeva.pet.model.animal.dog.DogBreed;
import org.lebedeva.pet.repository.DogBreedRepository;
import org.lebedeva.pet.service.DogBreedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DogBreedServiceImpl implements DogBreedService {

    private final DogBreedRepository dogBreedRepository;
    private final DogBreedMapper dogBreedMapper;

    public DogBreedServiceImpl(DogBreedRepository dogBreedRepository, DogBreedMapper dogBreedMapper) {
        this.dogBreedRepository = dogBreedRepository;
        this.dogBreedMapper = dogBreedMapper;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<DogBreedDto> findDogBreedsDto() {
        return dogBreedRepository.findAll().stream()
                .map(dogBreedMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DogBreed save(DogBreedDto dto) {
        DogBreed dogBreed = dogBreedMapper.toEntity(dto);
        return dogBreedRepository.save(dogBreed);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<DogBreedDto> findAll(Pageable pageable) {
        return dogBreedRepository.findAll(pageable)
                .map(dogBreedMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<DogBreedDto> findById(Integer id) {
        return dogBreedRepository.findById(id)
                .map(dogBreedMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        dogBreedRepository.deleteById(id);
    }
}
