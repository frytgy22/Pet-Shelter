package org.lebedeva.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/403")
    public String error() {
        return "access_denied";
    }
}
