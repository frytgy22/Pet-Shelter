package org.lebedeva.pet.mapper.post;

import org.lebedeva.pet.dto.post.CategoryDto;
import org.lebedeva.pet.model.post.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface CategoryMapper {

    Category toEntity(CategoryDto dto);

    CategoryDto toDto(Category entity);
}
