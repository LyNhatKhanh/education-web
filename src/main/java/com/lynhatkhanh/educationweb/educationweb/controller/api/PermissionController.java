package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dao.PermissionRepository;
import com.lynhatkhanh.educationweb.educationweb.dto.request.PermissionRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.ApiResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.PermissionResponse;
import com.lynhatkhanh.educationweb.educationweb.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    IPermissionService permissionService;
    private final PermissionRepository permissionRepository;

    @GetMapping("/{name}")
    public ApiResponse<PermissionResponse> getPermissions(@PathVariable("name") String name) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.getPermission(name))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getPermissions())
                .build();
    }

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(request))
                .build();
    }

    @PutMapping("/{name}")
    public ApiResponse<PermissionResponse> updatePermission(@PathVariable("name") String name,
                                                            @RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.updatePermission(name, request))
                .message("Update complete!")
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse<Void> deletePermission(@PathVariable("name") String name) {
        PermissionResponse permission = permissionService.getPermission(name);
        permissionService.deletePermission(name);
        return ApiResponse.<Void>builder()
                .message("Delete permission: " + permission.getName() + " complete!")
                .build();
    }

}
