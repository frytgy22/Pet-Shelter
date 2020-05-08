package org.lebedeva.pet.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private UserController controller;

    @Autowired
    private MockMvc mock;

    @Test
    @WithUserDetails("admin@1.ua")
    public void index() throws Exception {
        Assertions.assertThat(controller).isNotNull();

        this.mock.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("Users")));
    }

    @Test
    @WithUserDetails("admin@1.ua")
    public void delete() throws Exception {
        this.mock.perform(get("/users/delete/2"))
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    @WithUserDetails("admin@1.ua")
    public void create() throws Exception {
        this.mock.perform(post("/users/create").with(csrf())
                .param("email", "hello@1.ua")
                .param("name", "hello")
                .param("password", "1")
                .param("confirmPassword", "1"))
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    @WithUserDetails("admin@1.ua")
    public void failRegistration() throws Exception {
        this.mock.perform(get("/users/registration"))
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/403"));
    }

    @Test
    public void successRegistration() throws Exception {
        this.mock.perform(post("/users/registration").with(csrf())
                .param("email", "hello@122.ua")
                .param("name", "hello")
                .param("password", "1")
                .param("confirmPassword", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
