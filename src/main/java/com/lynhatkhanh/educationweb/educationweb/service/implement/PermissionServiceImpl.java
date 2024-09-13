package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.PermissionRepository;
import com.lynhatkhanh.educationweb.educationweb.dto.request.PermissionRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.PermissionResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Permission;
import com.lynhatkhanh.educationweb.educationweb.exception.AppException;
import com.lynhatkhanh.educationweb.educationweb.exception.ErrorCode;
import com.lynhatkhanh.educationweb.educationweb.mapper.PermissionMapper;
import com.lynhatkhanh.educationweb.educationweb.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements IPermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse getPermission(String name) {
        Permission permission = permissionRepository.findById(name.toUpperCase())
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXISTED));
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permission -> permissionMapper.toPermissionResponse(permission))
                .toList();
    }

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        if (permissionRepository.existsByName(request.getName().toUpperCase()))
            throw new AppException(ErrorCode.PERMISSION_EXISTED);

        request.setName(request.getName().toUpperCase());
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse updatePermission(String name, PermissionRequest request) {
        Permission permission = permissionRepository.findById(name.toUpperCase())
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXISTED));

        if (permissionRepository.existsByName(request.getName().toUpperCase())
                && !permission.getName().equalsIgnoreCase(request.getName()))
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        permissionMapper.updatePermission(permission, request);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public void deletePermission(String name) {
        permissionRepository.deleteById(name.toUpperCase());
    }
}
