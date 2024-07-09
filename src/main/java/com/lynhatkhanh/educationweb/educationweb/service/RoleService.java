package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(int theId);
}
