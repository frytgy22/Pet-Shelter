package org.lebedeva.pet.repository;

import lombok.NonNull;
import org.lebedeva.pet.model.post.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByName(@NonNull String name);

    @Query("select category from Category category left join fetch category.posts where category.id =:id")
    Optional<Category> findOneWithEagerRelationships(@Param("id") Integer id);
}
