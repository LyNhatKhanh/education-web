package com.lynhatkhanh.educationweb.educationweb.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping({"/index", "/index.html", "/"})
    public String showIndex() {
        return "home/index";
    }

}
