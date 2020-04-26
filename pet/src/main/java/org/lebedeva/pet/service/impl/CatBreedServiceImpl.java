package org.lebedeva.pet.service.impl;

import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.mapper.CatBreedMapper;
import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.lebedeva.pet.repository.CatBreedRepository;
import org.lebedeva.pet.service.CatBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        CatBreed breed = catBreedRepository.findOneWithEagerRelationships(dto.getId()).orElse(new CatBreed());
        breed.setName(dto.getName());
        return catBreedRepository.save(breed);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<CatBreedDto> findAll(Pageable pageable) {

        return catBreedRepository.findAll(pageable)
                .map(catBreedMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<CatBreedDto> findById(Integer id) throws Exception {
        CatBreed breed = catBreedRepository.findById(id).orElse(null);
        return breed != null ?
                Optional.of(catBreedMapper.toDto(breed))
                : Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        catBreedRepository.deleteById(id);
    }
}
