package org.lebedeva.pet.controller.post;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.pet.dto.post.CategoryDto;
import org.lebedeva.pet.dto.post.PostDto;
import org.lebedeva.pet.model.weather.Weather;
import org.lebedeva.pet.service.CategoryService;
import org.lebedeva.pet.service.PostService;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(PostController.BASE_URL)
public class PostController {

    public static final String VIEW_PATH = "post";
    public static final String BASE_URL = "/posts";
    public static final String UPLOADS_DIR = "/posts/";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INFO_PATH = VIEW_PATH + "/info";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;
    public static final String URL_WEATHER =
            "http://api.openweathermap.org/data/2.5/weather?q=Dnipro&appid=bc5422a1c3cfc20a7193c6d338279896";

    private final PostService postService;
    private final RestTemplate restTemplate;
    private final CategoryService categoryService;
    private final UploadFileService uploadFileService;
    private final MultipartFileValidator fileValidator;

    public PostController(PostService postService,
                          RestTemplate restTemplate,
                          CategoryService categoryService,
                          UploadFileService uploadFileService,
                          MultipartFileValidator fileValidator) {
        this.postService = postService;
        this.restTemplate = restTemplate;
        this.fileValidator = fileValidator;
        this.categoryService = categoryService;
        this.uploadFileService = uploadFileService;
    }

    @ModelAttribute("message")
    public void setMessage() {
    }

    @ModelAttribute("categories")
    List<CategoryDto> getCategories() {
        return categoryService.findCategoriesDto();
    }

    @ModelAttribute("weather")
    public Weather weather(Model model) {
        Weather weather = restTemplate.getForObject(URL_WEATHER, Weather.class);

        if (weather != null) {
            model.addAttribute("temp", Math.round(weather.getMain().getTemp() - 273.15));
            model.addAttribute("icon", "https://openweathermap.org/img/wn/" +
                    weather.getWeather().get(0).getIcon() + "@2x.png");
            return weather;
        }
        return new Weather();
    }

    @GetMapping()
    public String index(Model model, Integer page, Integer size, String search, String category) {
        Pageable pageable = PageRequest.of(
                page == null ? 0 : page,
                size == null ? 3 : size,
                Sort.by("publicationDate").descending());

        Page<PostDto> postDtoPage;

        if (search != null && !"".equals(search)) {
            postDtoPage = postService.findAllByContainsTitle(search, pageable);
        } else if (category != null && !"".equals(category)) {

            try {
                postDtoPage = postService.findAllByCategory(
                        categoryService.findCategoryByName(category).orElseThrow(Exception::new), pageable);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage(), e);
                postDtoPage = postService.findAll(pageable);
            }
        } else {
            postDtoPage = postService.findAll(pageable);
        }

        model.addAttribute("url", BASE_URL);
        model.addAttribute("page", postDtoPage);
        model.addAttribute("posts", postDtoPage.getContent());
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("postDto", new PostDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute PostDto postDto,
                         BindingResult bindingResult,
                         RedirectAttributes attributes,
                         MultipartFile img) {

        if (img != null) {
            // fileValidator.validate(img, bindingResult);//TODO fix video
        }
        if (!bindingResult.hasErrors()) {
            try {
                if (img != null && !img.isEmpty()) {
                    postDto.setFile(img.getOriginalFilename());
                    uploadFileService.uploadFile(img, UPLOADS_DIR, postService.save(postDto).getId());
                } else {
                    postService.save(postDto);
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
            postService.delete(id);
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
            model.addAttribute("postDto", postService.findById(id).orElseThrow(Exception::new));
            return FORM_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }

    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute PostDto postDto,
                       BindingResult bindingResult,
                       RedirectAttributes attributes,
                       MultipartFile img) {
        return create(postDto, bindingResult, attributes, img);
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable int id, Model model) {
        try {
            model.addAttribute("post", postService.findById(id).orElseThrow(Exception::new));
            return INFO_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }
}
