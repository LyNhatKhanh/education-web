package com.lynhatkhanh.educationweb.educationweb.controller;

import com.lynhatkhanh.educationweb.educationweb.exception.DuplicateUsernameException;
import com.lynhatkhanh.educationweb.educationweb.model.Role;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.model.UserRole;
import com.lynhatkhanh.educationweb.educationweb.service.RoleService;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {

    private UserAccountService userAccountService;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserAccountService userAccountService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPageWithMessage(@RequestParam(value = "message", required = false) String message, Model model) {
        if (message != null)
            MessageUtil.showMessage(message, model);
        return "/login/index";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "login/access-denied";
    }


    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model theModel) {
        UserAccount newUser = new UserAccount();
        theModel.addAttribute("newUser", newUser);

        List<String> genderOption = Arrays.asList("Male", "Female", "Another");
        theModel.addAttribute("genderOption", genderOption);

        return "login/register";
    }

    @PostMapping("/processSignUp")
    public String addNewMember(
            @Valid @ModelAttribute("newUser") UserAccount newUser,
            BindingResult theBindingResult, Model theModel) {

        System.out.println("Binding results: " + theBindingResult.toString());
        if (theBindingResult.hasErrors()) {
            List<String> genderOption = Arrays.asList("Male", "Female", "Another");
            theModel.addAttribute("genderOption", genderOption);
            return "login/register";
        } else {
            String pw = newUser.getPassword();
            newUser.setPassword(passwordEncoder.encode(pw));
            newUser.setEnabled(true);

            // set role Student (auto set while create user)
            // => handle login error (JOIN FETCH at UserAccountRepository.findUserAccountAndRoleByUserName(userName))
            newUser.setUserRole(new HashSet<>());
            newUser.getUserRole().add(new UserRole(roleService.findById(2), newUser));

            newUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            try {
                userAccountService.save(newUser);
                return "redirect:/showMyLoginPage?message=insert_success";
            } catch (DuplicateUsernameException e) {
                theModel.addAttribute("errorMessage", e.getMessage());

                List<String> genderOption = Arrays.asList("Male", "Female", "Another");
                theModel.addAttribute("genderOption", genderOption);
                return "login/register";
            }


        }
    }


}
