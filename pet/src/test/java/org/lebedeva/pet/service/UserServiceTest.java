package org.lebedeva.pet.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.dto.user.UserDto;
import org.lebedeva.pet.model.user.User;
import org.lebedeva.pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private UserDto userDto;

    @Before
    public void init() {
        userDto = new UserDto(null, "test", "heko@12", "pass", "pass",
                new HashSet<>(), false);
    }

    @Test
    public void test() {
        User user = userService.save(userDto);
        assertNotNull(userService);

        assertNotNull(user);
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.isSubscribe(), user.isSubscribe());

        UserDto newUser = userService.findById(user.getId()).orElse(null);

        assertNotNull(newUser);
        assertEquals(newUser.getName(), user.getName());
        assertEquals(newUser.getEmail(), user.getEmail());
        assertEquals(newUser.getPassword(), user.getPassword());
        assertEquals(newUser.isSubscribe(), user.isSubscribe());

        Page<UserDto> all = userService.findAll(PageRequest.of(0, user.getId()));

        assertNotNull(all);
        assertTrue(all.stream()
                .anyMatch(userDto1 -> userDto1.getId().equals(user.getId())));

        userService.delete(user.getId());

        Page<UserDto> withoutUser = userService.findAll(PageRequest.of(0, user.getId()));
        assertTrue(withoutUser.stream()
                .noneMatch(userDto1 -> userDto1.getId().equals(user.getId())));
    }
}