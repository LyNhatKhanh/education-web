package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*") // mark all browser can access web server, if not => only server-computer can access
@RequestMapping("/api")
public class RestControllerClass {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserAccount>> findAll() {
        return new ResponseEntity<>(userAccountService.getUsersOfRole(1,0), HttpStatus.OK);
    }

    @GetMapping("/student")
    public ResponseEntity<Iterable<UserAccount>> findStudent() {
        return new ResponseEntity<>(userAccountService.getUsersOfRole(1,2), HttpStatus.OK);
    }

}
