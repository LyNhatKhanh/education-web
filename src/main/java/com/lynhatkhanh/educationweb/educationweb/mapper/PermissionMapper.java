package com.lynhatkhanh.educationweb.educationweb.mapper;

import com.lynhatkhanh.educationweb.educationweb.dto.request.PermissionRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.PermissionResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
