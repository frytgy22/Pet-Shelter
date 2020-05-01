package org.lebedeva.pet.service;

import lombok.NonNull;
import org.lebedeva.pet.dto.post.PostDto;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.model.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface PostService extends GenericService<PostDto, Post> {
    Page<PostDto> findAllByContainsTitle(@NonNull @NotNull String title, Pageable pageable);

    Page<PostDto> findAllByCategory(@NonNull @NotNull Category category, Pageable pageable);
}
