package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.UserCreationRequest;
import com.lynhatkhanh.educationweb.educationweb.entity.User;

import java.util.List;

public interface IUserService {

    User createUser(UserCreationRequest request);

    List<User> getAll();
}
