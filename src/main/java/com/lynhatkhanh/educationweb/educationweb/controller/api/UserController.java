package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dto.request.UserCreationRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.UserUpdateRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.ApiResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.UserResponse;
import com.lynhatkhanh.educationweb.educationweb.service.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    IUserService userService;

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: " + context.getName());
        context.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }


    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    /* TODO: fix Bug */
    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId,
                                                @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .message("Update complete!")
                .build();
    }

    /* TODO: fix Bug */
    @DeleteMapping("/{userId}")
    public ApiResponse deleteUser(@PathVariable("userId") String userId) {
        UserResponse user = userService.getUser(userId);
        userService.deleteUser(userId);
        return ApiResponse.builder()
                .message("Delete user: " + user.getUsername() + " complete!")
                .build();
    }


}
