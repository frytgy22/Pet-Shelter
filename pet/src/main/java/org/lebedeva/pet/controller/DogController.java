package org.lebedeva.pet.controller;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.pet.dto.dog.DogBreedDto;
import org.lebedeva.pet.dto.dog.DogDto;
import org.lebedeva.pet.service.DogBreedService;
import org.lebedeva.pet.service.DogService;
import org.lebedeva.pet.service.UploadFileService;
import org.lebedeva.pet.validator.MultipartFileValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(DogController.BASE_URL)
public class DogController {

    public static final String VIEW_PATH = "dog";
    public static final String BASE_URL = "/dogs";
    public static final String UPLOADS_DIR = "/dogs/";
    public static final String INFO_PATH = VIEW_PATH + "/info";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;

    private final DogService dogService;
    private final DogBreedService dogBreedService;
    private final UploadFileService uploadFileService;
    private final MultipartFileValidator fileValidator;

    public DogController(DogService dogService,
                         DogBreedService dogBreedService,
                         UploadFileService uploadFileService,
                         MultipartFileValidator fileValidator) {
        this.dogService = dogService;
        this.dogBreedService = dogBreedService;
        this.uploadFileService = uploadFileService;
        this.fileValidator = fileValidator;
    }

    @ModelAttribute("dogBreeds")
    List<DogBreedDto> getGroups() {
        return dogBreedService.findDogBreedsDto();
    }

    @ModelAttribute("message")
    public void setMessage() {
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size, String sort) {
        sort = sort == null ? "id" : sort;

        Pageable pageable = PageRequest.of(
                page == null ? 0 : page,
                size == null ? 5 : size,
                Sort.by(sort));
        Page<DogDto> dogsDtoPage = dogService.findAll(pageable);

        model.addAttribute("sort", sort);
        model.addAttribute("url", BASE_URL);
        model.addAttribute("page", dogsDtoPage);
        model.addAttribute("dogs", dogsDtoPage.getContent());
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("dogDto", new DogDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute DogDto dogDto,
                         BindingResult bindingResult,
                         RedirectAttributes attributes,
                         MultipartFile img) {

        if (img != null) {
            fileValidator.validate(img, bindingResult);
        }
        if (!bindingResult.hasErrors()) {
            try {
                if (img != null && !img.isEmpty()) {
                    dogDto.setPhoto(img.getOriginalFilename());
                    uploadFileService.uploadFile(img, UPLOADS_DIR, dogService.save(dogDto).getId());
                } else {
                    dogService.save(dogDto);
                }
                attributes.addFlashAttribute("message", "Saved successfully!");
            } catch (Exception e) {
                attributes.addFlashAttribute("message", "Saving failed!");
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
            dogService.delete(id);
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
            model.addAttribute("dogDto", dogService.findById(id).orElseThrow(Exception::new));
            return FORM_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }

    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute DogDto dogDto,
                       BindingResult bindingResult,
                       RedirectAttributes attributes,
                       MultipartFile img) {
        return create(dogDto, bindingResult, attributes, img);
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        try {
            model.addAttribute("dog", dogService.findById(id).orElseThrow(Exception::new));
            return INFO_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }
}
