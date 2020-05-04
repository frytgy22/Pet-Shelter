package org.lebedeva.pet.controller.animal;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.pet.dto.dog.DogBreedDto;
import org.lebedeva.pet.service.DogBreedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;

@Slf4j
@Controller
@RolesAllowed("ROLE_ADMIN")
@RequestMapping(DogBreedController.BASE_URL)
public class DogBreedController {

    public static final String VIEW_PATH = "dog/breed";
    public static final String BASE_URL = "/dogs/breeds";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;

    private final DogBreedService dogBreedService;

    public DogBreedController(DogBreedService dogBreedService) {
        this.dogBreedService = dogBreedService;
    }

    @ModelAttribute("message")
    public void setMessage() {
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size, Sort.by("name"));
        Page<DogBreedDto> dogBreedsPage = dogBreedService.findAll(pageable);

        model.addAttribute("url", BASE_URL);
        model.addAttribute("page", dogBreedsPage);
        model.addAttribute("dogBreeds", dogBreedsPage.getContent());
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("dogBreedDto", new DogBreedDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute DogBreedDto dogBreedDto,
                         BindingResult bindingResult,
                         RedirectAttributes attributes) {

        if (!bindingResult.hasErrors()) {
            try {
                dogBreedService.save(dogBreedDto);
                attributes.addFlashAttribute("message", "Saved successfully!");
            } catch (Exception e) {
                attributes.addFlashAttribute("message", "Saving failed. This breed already exists!");
                log.error(e.getLocalizedMessage(), e);
            }
            return REDIRECT_INDEX;
        }
        log.error(bindingResult.toString());
        return FORM_PATH;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes attributes) {
        try {
            dogBreedService.delete(id);
            attributes.addFlashAttribute("message", "Deleted successfully!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Deletion failed!");
            log.error(e.getLocalizedMessage(), e);
        }
        return REDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        try {
            model.addAttribute("dogBreedDto", dogBreedService.findById(id).orElseThrow(Exception::new));
            return FORM_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }

    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute DogBreedDto dogBreedDto,
                       BindingResult bindingResult,
                       RedirectAttributes attributes) {
        return create(dogBreedDto, bindingResult, attributes);
    }
}
