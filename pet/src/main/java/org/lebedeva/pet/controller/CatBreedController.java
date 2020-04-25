package org.lebedeva.pet.controller;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.pet.dto.cat.CatBreedDto;
import org.lebedeva.pet.service.CatBreedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(CatBreedController.BASE_URL)
public class CatBreedController {

    public static final String VIEW_PATH = "cat/breed";
    public static final String BASE_URL = "/cats/breeds";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;

    private Pageable pageable;
    private final CatBreedService catBreedService;

    public CatBreedController(CatBreedService catBreedService) {
        this.catBreedService = catBreedService;
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<CatBreedDto> catBreeds = catBreedService.findAll(pageable);
        model.addAttribute("catBreeds", catBreeds.getContent());
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("catBreedDto", new CatBreedDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute CatBreedDto catBreedDto,
                         BindingResult bindingResult) {
        log.info("cat: {}", catBreedDto);
        if (!bindingResult.hasErrors()) {
            try {
                catBreedService.save(catBreedDto);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage(), e);
            }
            return REDIRECT_INDEX;
        }
        log.error(bindingResult.toString());
        return FORM_PATH;
    }
}
