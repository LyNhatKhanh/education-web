package com.lynhatkhanh.educationweb.educationweb.controller.admin;

import com.lynhatkhanh.educationweb.educationweb.exception.DuplicateUsernameException;
import com.lynhatkhanh.educationweb.educationweb.model.*;
import com.lynhatkhanh.educationweb.educationweb.service.*;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"/index", "/index.html", ""})
    public String showIndex(Model model) {
        CustomUserDetail user = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "admin/index";
    }

    @GetMapping("/dashboard")
    public String showDashBoard() {
        return "admin/dashboard";
    }

}
