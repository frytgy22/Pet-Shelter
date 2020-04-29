package org.lebedeva.pet.mapper.post;

import org.lebedeva.pet.dto.post.PostDto;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.model.post.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    default Post toEntity(PostDto dto) throws EnumConstantNotPresentException {
        return new Post(dto.getId(), dto.getTitle(), dto.getSubtitle(), dto.getContents(),
                dto.getPublicationDate(), Category.valueOf(dto.getCategory()), dto.getPhoto());
    }

    default PostDto toDto(Post entity) {
        return new PostDto(entity.getId(), entity.getTitle(), entity.getSubtitle(), entity.getContents(),
                entity.getPublicationDate(), entity.getCategory().toString(), entity.getPhoto());
    }
}
