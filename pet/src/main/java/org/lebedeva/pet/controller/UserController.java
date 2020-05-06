package org.lebedeva.pet.controller;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.pet.dto.user.UserDto;
import org.lebedeva.pet.model.user.Role;
import org.lebedeva.pet.model.user.User;
import org.lebedeva.pet.service.UserService;
import org.lebedeva.pet.validator.UserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String VIEW_PATH = "user";
    public static final String BASE_URL = "/users";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;
    public static final String REGISTRATION_PATH = VIEW_PATH + "/registration";

    private final UserService userService;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          UserValidator userValidator,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("message")
    public void setMessage() {
    }

    @ModelAttribute("userRoles")
    public Role[] getRoles() {
        try {
            return Role.values();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return new Role[0];
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 10 : size, Sort.by("id"));
        Page<UserDto> usersDtoPage = userService.findAll(pageable);

        model.addAttribute("url", BASE_URL);
        model.addAttribute("page", usersDtoPage);
        model.addAttribute("users", usersDtoPage.getContent());
        return INDEX_PATH;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDto", new UserDto());
        return REGISTRATION_PATH;
    }

    @PostMapping("/registration")
    public String registration(@Validated @ModelAttribute UserDto userDto,
                               BindingResult bindingResult,
                               RedirectAttributes attributes) {
        userValidator.validate(userDto, bindingResult);

        if (!bindingResult.hasErrors()) {
            try {
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                userDto.getRoles().add(Role.ROLE_USER);

                User user = userService.save(userDto);
                setUserContext(userDto.getPassword(), user);

                attributes.addFlashAttribute("message", "Registered successfully!");
            } catch (Exception e) {
                attributes.addFlashAttribute("message", "Register failed! This email already exists!");
                log.error(e.getLocalizedMessage(), e);
            }
            return "redirect:/";
        }
        log.error(bindingResult.toString());
        return REGISTRATION_PATH;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes attributes) {
        try {
            userService.delete(id);
            attributes.addFlashAttribute("message", "Deleted successfully!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Deletion failed!");
            log.error(e.getLocalizedMessage(), e);
        }
        return REDIRECT_INDEX;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("userDto", new UserDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute UserDto userDto,
                         BindingResult bindingResult,
                         RedirectAttributes attributes) {
        userValidator.validate(userDto, bindingResult);

        if (!bindingResult.hasErrors()) {
            try {
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                userService.save(userDto);

                attributes.addFlashAttribute("message", "Saved successfully!");
            } catch (Exception e) {
                attributes.addFlashAttribute("message", "Saving failed. This email already exists!");
                log.error(e.getLocalizedMessage(), e);
            }
            return REDIRECT_INDEX;
        }
        log.error(bindingResult.toString());
        return FORM_PATH;
    }

    @GetMapping("/google")
    public String google(Authentication authentication, Model model) {
        if (authentication instanceof OAuth2AuthenticationToken) {

            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            String name = oAuth2User.getAttribute("name");
            String email = oAuth2User.getAttribute("email");
            String password = UUID.randomUUID().toString();

            User user = new User(name, email, password);

            SecurityContextHolder.clearContext();//logout
            setUserContext(password, user);
        }
        return "redirect:/";
    }

    private void setUserContext(@ModelAttribute @Validated String password, User user) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, password, user.getAuthorities());

        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}
