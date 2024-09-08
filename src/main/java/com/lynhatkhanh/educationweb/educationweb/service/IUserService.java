package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.UserCreationRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.UserUpdateRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.UserResponse;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IUserService {

    UserResponse getMyInfo();

    UserResponse createUser(UserCreationRequest request);

    @PreAuthorize("hasRole('ADMIN')")
    List<UserResponse> getUsers();

    @PostAuthorize("returnObject.username == authentication.name")
    UserResponse getUser(String userId);

    @PreAuthorize("hasAuthority('APPROVE_POST')")
    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
