package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.PermissionRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse getPermission(String name);
    List<PermissionResponse> getPermissions();
    PermissionResponse createPermission(PermissionRequest request);
    PermissionResponse updatePermission(String name, PermissionRequest request);
    void deletePermission(String name);
}
