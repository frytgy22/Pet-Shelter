package org.lebedeva.pet.service.impl;

import lombok.NonNull;
import org.lebedeva.pet.dto.post.CategoryDto;
import org.lebedeva.pet.mapper.post.CategoryMapper;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.repository.CategoryRepository;
import org.lebedeva.pet.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryDto> findCategoriesDto() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<Category> findCategoryByName(@NonNull String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public Category save(CategoryDto dto) {
        Category category = categoryRepository.findOneWithEagerRelationships(dto.getId()).orElse(new Category());
        category.setName(dto.getName().toLowerCase());
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<CategoryDto> findById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
