package com.lynhatkhanh.educationweb.educationweb.controller;

import com.lynhatkhanh.educationweb.educationweb.dao.MemberDao;
import com.lynhatkhanh.educationweb.educationweb.model.Member;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private MemberDao memberDao;

    @Autowired
    public LoginController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "/login/index";
    }


    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model theModel) {
        Member newMember = new Member();
        theModel.addAttribute("newMember", newMember);

        return "login/register";
    }

    @PostMapping("/member/process")
    public String addNewMember(
            @Valid @ModelAttribute("newMember") Member newMember,
            BindingResult theBindingResult) {

        System.out.println("Binding results: " + theBindingResult.toString());
        if (theBindingResult.hasErrors())
            return "login/register";
        else {
            String pw = newMember.getPassword();
            newMember.setPassword("{noop}" + pw);
            newMember.setActive(1);
            memberDao.save(newMember);
            return "redirect:/showMyLoginPage";
        }
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "login/access-denied";
    }
}
