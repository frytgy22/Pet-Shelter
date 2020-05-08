package org.lebedeva.pet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.post.CategoryDto;
import org.lebedeva.pet.dto.post.PostDto;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.model.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void test() {
        CategoryDto categoryDto = new CategoryDto("TEST");
        Category category = categoryService.save(categoryDto);

        assertNotNull(category);
        assertEquals(category.getName(), categoryDto.getName());

        Integer categoryId = category.getId();
        assertNotNull(categoryId);

        String categoryName = category.getName();
        assertNotNull(categoryName);

        PostDto postDto = new PostDto(null, "132", "123", "123", "", "",
                Collections.singleton(categoryId), Collections.singleton(categoryName), LocalDate.now());

        Post post = postService.save(postDto);

        assertNotNull(post);
        assertFalse(post.getCategories().add(category));
        assertEquals(post.getTitle(), postDto.getTitle());
        assertEquals(post.getSubtitle(), postDto.getSubtitle());
        assertEquals(post.getContents(), postDto.getContents());
        assertEquals(post.getCategories().size(), postDto.getCategoryId().size());


        Integer postId = post.getId();
        assertNotNull(postId);

        PostDto byId = postService.findById(postId).orElse(null);

        assertNotNull(byId);
        assertEquals(byId.getTitle(), postDto.getTitle());
        assertEquals(byId.getSubtitle(), postDto.getSubtitle());
        assertEquals(byId.getContents(), postDto.getContents());
        assertEquals(byId.getCategoryId(), postDto.getCategoryId());


        Page<PostDto> all = postService.findAll(PageRequest.of(0, post.getId()));

        assertNotNull(all);
        assertTrue(all.stream()
                .anyMatch(p -> p.getId().equals(post.getId())));

        postService.delete(post.getId());

        Page<PostDto> withoutPost = postService.findAll(PageRequest.of(0, post.getId()));
        assertTrue(withoutPost.stream()
                .noneMatch(p -> p.getId().equals(post.getId())));

        categoryService.delete(categoryId);
    }
}
