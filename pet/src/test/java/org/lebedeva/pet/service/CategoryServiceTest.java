package org.lebedeva.pet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.post.CategoryDto;
import org.lebedeva.pet.model.post.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CategoryServiceTest {

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

        CategoryDto byId = categoryService.findById(categoryId).orElse(null);

        assertNotNull(byId);
        assertEquals(byId.getName(), category.getName());
        assertEquals(byId.getId(), category.getId());

        Page<CategoryDto> all = categoryService.findAll(PageRequest.of(0, category.getId()));

        assertNotNull(all);
        assertTrue(all.stream()
                .anyMatch(c -> c.getId().equals(category.getId())));

        categoryService.delete(category.getId());

        Page<CategoryDto> withoutCategory = categoryService.findAll(PageRequest.of(0, category.getId()));
        assertTrue(withoutCategory.stream()
                .noneMatch(c -> c.getId().equals(category.getId())));
    }
}
