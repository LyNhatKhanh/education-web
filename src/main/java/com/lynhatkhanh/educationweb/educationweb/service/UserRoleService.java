package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findAll();

    UserRole findById(int id);

    void save(UserRole userRole);

}
