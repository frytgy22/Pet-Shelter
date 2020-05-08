package org.lebedeva.pet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.model.animal.cat.CatBreed;
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
public class CatBreedServiceTest {

    @Autowired
    private CatBreedService catBreedService;

    @Test
    public void test() {
        CatBreedDto catBreedDto = new CatBreedDto("TEST");
        CatBreed catBreed = catBreedService.save(catBreedDto);

        assertNotNull(catBreed);
        assertEquals(catBreed.getName(), catBreedDto.getName());

        Integer catBreedId = catBreed.getId();
        assertNotNull(catBreedId);

        CatBreedDto byId = catBreedService.findById(catBreedId).orElse(null);

        assertNotNull(byId);
        assertEquals(byId.getName(), catBreed.getName());
        assertEquals(byId.getId(), catBreed.getId());

        Page<CatBreedDto> all = catBreedService.findAll(PageRequest.of(0, catBreed.getId()));

        assertNotNull(all);
        assertTrue(all.stream()
                .anyMatch(c -> c.getId().equals(catBreed.getId())));

        catBreedService.delete(catBreed.getId());

        Page<CatBreedDto> withoutCatBreed = catBreedService.findAll(PageRequest.of(0, catBreed.getId()));
        assertTrue(withoutCatBreed.stream()
                .noneMatch(c -> c.getId().equals(catBreed.getId())));
    }
}
