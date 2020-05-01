package org.lebedeva.pet.repository;

import lombok.NonNull;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.model.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllByTitleContainsIgnoreCase(@NonNull @NotNull String title, Pageable pageable);

    Page<Post> findAllByCategoryContainsIgnoreCase(@NonNull @NotNull Category category, Pageable pageable);
}
