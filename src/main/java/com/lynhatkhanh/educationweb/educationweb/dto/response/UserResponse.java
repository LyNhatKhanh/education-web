package com.lynhatkhanh.educationweb.educationweb.dto.response;

import com.lynhatkhanh.educationweb.educationweb.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String id;
    String username;
    String firstName;
    String lastName;
    String address;
    String telephone;
    String gender;
    Boolean enabled;
    String email;
    LocalDate dob;
    Set<RoleResponse> roles;

}
