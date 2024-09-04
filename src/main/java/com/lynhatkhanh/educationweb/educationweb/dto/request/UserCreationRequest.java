package com.lynhatkhanh.educationweb.educationweb.dto.request;

import com.lynhatkhanh.educationweb.educationweb.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    String username;
    String password;
    String firstName;
    String lastName;
    String address;
    String telephone;
    String gender;
    Boolean enabled;
    String email;
    Set<String> roles;

}
