package com.lynhatkhanh.educationweb.educationweb.controller;

import com.lynhatkhanh.educationweb.educationweb.exception.DuplicateUsernameException;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
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
import java.util.List;

@Controller
@RequestMapping
public class LoginController {

    private UserAccountService userAccountService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserAccountService userAccountService, PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    //    @GetMapping("/showMyLoginPage")
//    @ResponseBody
//    public String showMyLoginPage() {
//        return "/login/index";
//    }

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

        return "login/register1";
    }

    @PostMapping("/processSignUp")
    public String addNewMember(
            @Valid @ModelAttribute("newUser") UserAccount newUser,
            BindingResult theBindingResult, Model theModel) {

        System.out.println("Binding results: " + theBindingResult.toString());
        if (theBindingResult.hasErrors()) {
            List<String> genderOption = Arrays.asList("Male", "Female", "Another");
            theModel.addAttribute("genderOption", genderOption);
            return "login/register1";
        } else {
            String pw = newUser.getPassword();
            newUser.setPassword(passwordEncoder.encode(pw));

            newUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            try {
                userAccountService.save(newUser);
                return "redirect:/showMyLoginPage?message=insert_success";
            } catch (DuplicateUsernameException e) {
                theModel.addAttribute("errorMessage", e.getMessage());

                List<String> genderOption = Arrays.asList("Male", "Female", "Another");
                theModel.addAttribute("genderOption", genderOption);
                return "login/register1";
            }


        }
    }


}
