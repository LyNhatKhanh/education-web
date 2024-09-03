package com.lynhatkhanh.educationweb.educationweb.service.exclude;

import com.lynhatkhanh.educationweb.educationweb.model.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findAll();

    UserRole findById(int id);

    UserRole findUserRoleAndRoleByUserRoleId(int theId);

    void save(UserRole userRole);

}
