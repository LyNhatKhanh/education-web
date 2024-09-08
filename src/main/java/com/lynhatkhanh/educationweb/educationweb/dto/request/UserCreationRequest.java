package com.lynhatkhanh.educationweb.educationweb.dto.request;

import com.lynhatkhanh.educationweb.educationweb.entity.Role;
import com.lynhatkhanh.educationweb.educationweb.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "INVALID_USERNAME")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
    String firstName;
    String lastName;
    String address;
    String telephone;
    String gender;
    Boolean enabled;
    String email;

    @DobConstraint(min=18)
    LocalDate dob;
    Set<String> roles;

}
