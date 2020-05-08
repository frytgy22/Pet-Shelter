package org.lebedeva.pet.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.post.CategoryDto;
import org.lebedeva.pet.dto.post.PostDto;
import org.lebedeva.pet.dto.user.UserDto;
import org.lebedeva.pet.model.post.Category;
import org.lebedeva.pet.model.post.Post;
import org.lebedeva.pet.model.user.User;
import org.lebedeva.pet.repository.CategoryRepository;
import org.lebedeva.pet.repository.PostRepository;
import org.lebedeva.pet.repository.UserRepository;
import org.lebedeva.pet.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void save() {
        CategoryDto categoryDto = new CategoryDto("test");
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

        assertEquals(post.getTitle(),postDto.getTitle());
        assertEquals(post.getSubtitle(),postDto.getSubtitle());
        assertEquals(post.getContents(),postDto.getContents());
        assertEquals(post.getCategories().size(),postDto.getCategoryId().size());

        postService.findById()


        assertTrue(all.stream()
                .anyMatch(userDto1 -> userDto1.getId().equals(user.getId())));

        userService.delete(user.getId());

        Page<UserDto> withoutUser = userService.findAll(PageRequest.of(0, 5));
        assertTrue(withoutUser.stream()
                .noneMatch(userDto1 -> userDto1.getId().equals(user.getId())));
    }
}
