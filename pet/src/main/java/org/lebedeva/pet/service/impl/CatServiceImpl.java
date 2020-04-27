package org.lebedeva.pet.service.impl;

import org.lebedeva.pet.dto.cat.CatDto;
import org.lebedeva.pet.mapper.animal.cat.CatMapper;
import org.lebedeva.pet.model.animal.cat.Cat;
import org.lebedeva.pet.repository.CatRepository;
import org.lebedeva.pet.service.CatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final CatMapper catMapper;

    public CatServiceImpl(CatRepository catRepository, CatMapper catMapper) {
        this.catRepository = catRepository;
        this.catMapper = catMapper;
    }

    @Override
    public Cat save(CatDto dto) {
        Cat cat = catMapper.toEntity(dto);
        return catRepository.save(cat);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<CatDto> findAll(Pageable pageable) {
        return catRepository.findAll(pageable)
                .map(catMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<CatDto> findById(Integer id) {
        return catRepository.findById(id)
                .map(catMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        catRepository.deleteById(id);
    }
}
