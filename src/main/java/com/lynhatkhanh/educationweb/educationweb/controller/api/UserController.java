package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dto.request.UserCreationRequest;
import com.lynhatkhanh.educationweb.educationweb.entity.User;
import com.lynhatkhanh.educationweb.educationweb.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    IUserService userService;

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }


}
