package org.lebedeva.pet.service;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lebedeva.pet.controller.HomeController;
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
public class HomeControllerTest {

    @Autowired
    private HomeController controller;

    @Autowired
    private MockMvc mock;

    @Test
    public void index() throws Exception {
        Assertions.assertThat(controller).isNotNull();

        this.mock.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pet shelter")));
    }

    @Test
    public void contacts() throws Exception {
        Assertions.assertThat(controller).isNotNull();

        this.mock.perform(get("/contacts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Contacts")));
    }

    @Test
    public void about() throws Exception {
        Assertions.assertThat(controller).isNotNull();

        this.mock.perform(get("/about"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("About us")));
    }

    @Test
    public void accessDenied() throws Exception {

        this.mock.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLogin() throws Exception {
        this.mock.perform(post("/login").with(csrf()).param("custom_username", "admin@1.ua")
                .param("custom_password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentials() throws Exception {
        this.mock.perform(post("/login").with(csrf()).param("user", "user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=credentials"));
    }

    @Test
    @WithUserDetails("admin@1.ua")
    public void correctName() throws Exception {
        Assertions.assertThat(controller).isNotNull();

        this.mock.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<span>admin</span>")))
                .andExpect(authenticated());
    }
}
