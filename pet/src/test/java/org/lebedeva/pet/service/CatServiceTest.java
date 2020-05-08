package org.lebedeva.pet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.dto.cat.CatDto;
import org.lebedeva.pet.model.animal.cat.Cat;
import org.lebedeva.pet.model.animal.cat.CatBreed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CatServiceTest {

    @Autowired
    private CatService catService;

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

        CatDto catDto = new CatDto(catBreedId, LocalDate.of(2019, 3, 3), "BOY",
                "hello");

        Cat cat = catService.save(catDto);

        assertNotNull(cat);
        assertEquals(cat.getBirthDate(), catDto.getBirthDate());
        assertEquals(cat.getDescription(), catDto.getDescription());
        assertEquals(cat.getGender().toString(), catDto.getGender());
        assertEquals(cat.getBreed().getId(), catDto.getBreedId());

        Integer catId = cat.getId();
        assertNotNull(catId);

        CatDto byId = catService.findById(catId).orElse(null);

        assertNotNull(byId);
        assertEquals(byId.getBirthDate(), catDto.getBirthDate());
        assertEquals(byId.getDescription(), catDto.getDescription());
        assertEquals(byId.getGender(), catDto.getGender());
        assertEquals(byId.getBreedId(), catDto.getBreedId());

        Page<CatDto> all = catService.findAll(PageRequest.of(0, cat.getId()));

        assertNotNull(all);
        assertTrue(all.stream()
                .anyMatch(d -> d.getId().equals(cat.getId())));

        catService.delete(cat.getId());

        Page<CatDto> withoutCat = catService.findAll(PageRequest.of(0, cat.getId()));
        assertTrue(withoutCat.stream()
                .noneMatch(p -> p.getId().equals(cat.getId())));

        catBreedService.delete(catBreedId);
    }
}
