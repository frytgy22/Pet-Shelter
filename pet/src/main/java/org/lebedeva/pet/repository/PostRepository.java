package org.lebedeva.pet.repository;

import org.lebedeva.pet.model.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);
}
