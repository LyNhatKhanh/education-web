package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.PermissionRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.RoleRepository;
import com.lynhatkhanh.educationweb.educationweb.dto.request.RoleRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.RoleResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Permission;
import com.lynhatkhanh.educationweb.educationweb.entity.Role;
import com.lynhatkhanh.educationweb.educationweb.exception.AppException;
import com.lynhatkhanh.educationweb.educationweb.exception.ErrorCode;
import com.lynhatkhanh.educationweb.educationweb.mapper.RoleMapper;
import com.lynhatkhanh.educationweb.educationweb.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements IRoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse getRole(String name) {
        Role role = roleRepository.findById(name.toUpperCase())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> roleMapper.toRoleResponse(role))
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {
        request.setName(request.getName().toUpperCase());
        Role role = roleMapper.toRole(request);
        if (request.getPermissions() != null) {
            Set<Permission> permissions = new HashSet<>();
            permissionRepository.findAllById(request.getPermissions()).forEach(permission -> permissions.add(permission));
            role.setPermissions(permissions);
        }
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public RoleResponse updateRole(String name, RoleRequest request) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        roleMapper.updateRole(role, request);
        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public void deleteRole(String name) {
        roleRepository.deleteById(name.toUpperCase());
    }
}
