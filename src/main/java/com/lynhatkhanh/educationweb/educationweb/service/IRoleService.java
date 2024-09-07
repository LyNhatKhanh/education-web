package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.RoleRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse getRole(String name);
    List<RoleResponse> getRoles();
    RoleResponse createRole(RoleRequest request);
    RoleResponse updateRole(String name, RoleRequest request);
    void deleteRole(String name);

}
