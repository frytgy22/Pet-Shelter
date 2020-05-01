package org.lebedeva.pet.service.impl;

import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.mapper.animal.cat.CatBreedMapper;
import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.lebedeva.pet.repository.CatBreedRepository;
import org.lebedeva.pet.service.CatBreedService;
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
public class CatBreedServiceImpl implements CatBreedService {

    private final CatBreedRepository catBreedRepository;
    private final CatBreedMapper catBreedMapper;

    public CatBreedServiceImpl(CatBreedRepository catBreedRepository, CatBreedMapper catBreedMapper) {
        this.catBreedRepository = catBreedRepository;
        this.catBreedMapper = catBreedMapper;
    }

    @Override
    public CatBreed save(CatBreedDto dto) {
        CatBreed catBreed = catBreedMapper.toEntity(dto);
        return catBreedRepository.save(catBreed);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<CatBreedDto> findAll(Pageable pageable) {
        return catBreedRepository.findAll(pageable)
                .map(catBreedMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<CatBreedDto> findById(Integer id) {
        return catBreedRepository.findById(id)
                .map(catBreedMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        catBreedRepository.deleteById(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<CatBreedDto> findCatBreedsDto() {
        return catBreedRepository.findAll().stream()
                .map(catBreedMapper::toDto)
                .collect(Collectors.toList());
    }
}
