package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"/index", "/index.html", ""})
    public String showIndex(Model model) {
//        CustomUserDetail user = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", user);
        return "admin/index";
    }

    @GetMapping("/dashboard")
    public String showDashBoard() {
        return "admin/dashboard";
    }

}
