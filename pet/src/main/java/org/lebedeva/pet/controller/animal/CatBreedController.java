package org.lebedeva.pet.controller.animal;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.service.CatBreedService;
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
@RequestMapping(CatBreedController.BASE_URL)
public class CatBreedController {

    public static final String VIEW_PATH = "cat/breed";
    public static final String BASE_URL = "/cats/breeds";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;

    private final CatBreedService catBreedService;

    public CatBreedController(CatBreedService catBreedService) {
        this.catBreedService = catBreedService;
    }

    @ModelAttribute("message")
    public void setMessage() {
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 10 : size, Sort.by("name"));
        Page<CatBreedDto> catBreedsPage = catBreedService.findAll(pageable);

        model.addAttribute("url", BASE_URL);
        model.addAttribute("page", catBreedsPage);
        model.addAttribute("catBreeds", catBreedsPage.getContent());
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("catBreedDto", new CatBreedDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute CatBreedDto catBreedDto,
                         BindingResult bindingResult,
                         RedirectAttributes attributes) {

        if (!bindingResult.hasErrors()) {
            try {
                catBreedDto.setName(catBreedDto.getName().toUpperCase());
                catBreedService.save(catBreedDto);
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
            catBreedService.delete(id);
            attributes.addFlashAttribute("message", "Deleted successfully!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Deletion failed. You have cats of this breed!");
            log.error(e.getLocalizedMessage(), e);
        }
        return REDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        try {
            model.addAttribute("catBreedDto", catBreedService.findById(id).orElseThrow(Exception::new));
            return FORM_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }

    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute CatBreedDto catBreedDto,
                       BindingResult bindingResult,
                       RedirectAttributes attributes) {
        return create(catBreedDto, bindingResult, attributes);
    }
}
