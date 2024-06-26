package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dao.MemberDao;
import com.lynhatkhanh.educationweb.educationweb.model.Member;
import com.lynhatkhanh.educationweb.educationweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private MemberService memberService;

    @Autowired
    public ApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public Iterable<Member> findAll() {
        return memberService.findAll();
    }

}
