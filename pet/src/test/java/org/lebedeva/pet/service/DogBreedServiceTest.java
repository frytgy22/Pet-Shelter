package org.lebedeva.pet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.dog.DogBreedDto;
import org.lebedeva.pet.model.animal.dog.DogBreed;
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
public class DogBreedServiceTest {

    @Autowired
    private DogBreedService dogBreedService;

    @Test
    public void test() {
        DogBreedDto dogBreedDto = new DogBreedDto("TEST");
        DogBreed dogBreed = dogBreedService.save(dogBreedDto);

        assertNotNull(dogBreed);
        assertEquals(dogBreed.getName(), dogBreedDto.getName());

        Integer dogBreedId = dogBreed.getId();
        assertNotNull(dogBreedId);

        DogBreedDto byId = dogBreedService.findById(dogBreedId).orElse(null);

        assertNotNull(byId);
        assertEquals(byId.getName(), dogBreed.getName());
        assertEquals(byId.getId(), dogBreed.getId());

        Page<DogBreedDto> all = dogBreedService.findAll(PageRequest.of(0, dogBreed.getId()));

        assertNotNull(all);
        assertTrue(all.stream()
                .anyMatch(c -> c.getId().equals(dogBreed.getId())));

        dogBreedService.delete(dogBreed.getId());

        Page<DogBreedDto> withoutDogBreed = dogBreedService.findAll(PageRequest.of(0, dogBreed.getId()));
        assertTrue(withoutDogBreed.stream()
                .noneMatch(c -> c.getId().equals(dogBreed.getId())));
    }
}
