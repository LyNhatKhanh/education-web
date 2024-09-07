package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dto.request.RoleRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.ApiResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.RoleResponse;
import com.lynhatkhanh.educationweb.educationweb.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    IRoleService roleService;

    @GetMapping("/{name}")
    public ApiResponse<RoleResponse> getRole(@PathVariable("name") String name) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.getRole(name))
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getRoles())
                .build();
    }

    @PostMapping
    public ApiResponse<RoleResponse> createPermission(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    @PutMapping("/{name}")
    public ApiResponse<RoleResponse> updatePermission(@PathVariable("name") String name,
                                                            @RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.updateRole(name, request))
                .message("Update complete!")
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse deletePermission(@PathVariable("name") String name) {
        RoleResponse role = roleService.getRole(name);
        roleService.deleteRole(name);
        return ApiResponse.builder()
                .message("Delete role: " + role.getName() + " complete!")
                .build();
    }

}
