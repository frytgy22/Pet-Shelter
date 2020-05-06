package org.lebedeva.pet.mapper.post;

import org.lebedeva.pet.dto.post.PostDto;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.model.post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface PostMapper {

    @Mapping(target = "categories", ignore = true)
    @Mapping(source = "file", target = "postFile.file")
    @Mapping(source = "fileType", target = "postFile.fileType")
    Post toEntity(PostDto dto);

    default PostDto toDto(Post entity) {
        return new PostDto(entity.getId(), entity.getTitle(), entity.getSubtitle(), entity.getContents(),
                entity.getPostFile().getFile(), entity.getPostFile().getFileType(),
                entity.getCategories().stream()
                        .map(Category::getId)
                        .collect(Collectors.toSet()), entity.getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toSet()), entity.getCreated());
    }
}
