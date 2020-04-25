package org.lebedeva.pet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(CatController.BASE_URL)
public class CatController {

    public static final String VIEW_PATH = "cat";
    public static final String BASE_URL = "/cats";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;

    @GetMapping
    public String index(){
        return FORM_PATH;
    }
}
