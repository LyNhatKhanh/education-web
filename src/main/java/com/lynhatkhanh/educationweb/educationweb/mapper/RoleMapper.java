package com.lynhatkhanh.educationweb.educationweb.mapper;

import com.lynhatkhanh.educationweb.educationweb.dto.request.RoleRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.RoleResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
    @Mapping(target = "permissions", ignore = true)
    void updateRole(@MappingTarget Role role, RoleRequest request);
}
