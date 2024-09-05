package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.UserCreationRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.UserUpdateRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.UserResponse;

import java.util.List;

public interface IUserService {

    UserResponse getMyInfo();

    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUser(String userId);

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
