package org.lebedeva.pet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.dog.DogBreedDto;
import org.lebedeva.pet.dto.dog.DogDto;
import org.lebedeva.pet.model.animal.dog.Dog;
import org.lebedeva.pet.model.animal.dog.DogBreed;
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
public class DogServiceTest {

    @Autowired
    private DogService dogService;

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

        DogDto dogDto = new DogDto(dogBreedId, LocalDate.of(2019, 3, 3), "BOY",
                "hello");

        Dog dog = dogService.save(dogDto);

        assertNotNull(dog);
        assertEquals(dog.getBirthDate(), dogDto.getBirthDate());
        assertEquals(dog.getDescription(), dogDto.getDescription());
        assertEquals(dog.getGender().toString(), dogDto.getGender());
        assertEquals(dog.getBreed().getId(), dogDto.getBreedId());

        Integer dogId = dog.getId();
        assertNotNull(dogId);

        DogDto byId = dogService.findById(dogId).orElse(null);

        assertNotNull(byId);
        assertEquals(byId.getBirthDate(), dogDto.getBirthDate());
        assertEquals(byId.getDescription(), dogDto.getDescription());
        assertEquals(byId.getGender(), dogDto.getGender());
        assertEquals(byId.getBreedId(), dogDto.getBreedId());

        Page<DogDto> all = dogService.findAll(PageRequest.of(0, dog.getId()));

        assertNotNull(all);
        assertTrue(all.stream()
                .anyMatch(d -> d.getId().equals(dog.getId())));

        dogService.delete(dog.getId());

        Page<DogDto> withoutDog = dogService.findAll(PageRequest.of(0, dog.getId()));
        assertTrue(withoutDog.stream()
                .noneMatch(p -> p.getId().equals(dog.getId())));

        dogBreedService.delete(dogBreedId);
    }
}
