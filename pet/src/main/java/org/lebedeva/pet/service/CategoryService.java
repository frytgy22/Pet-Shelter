package org.lebedeva.pet.service;

import org.lebedeva.pet.dto.post.CategoryDto;
import org.lebedeva.pet.model.post.Category;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface CategoryService extends GenericService<CategoryDto, Category> {
    List<CategoryDto> findCategoriesDto();

    Optional<Category> findCategoryByName(@NotNull String name);
}
