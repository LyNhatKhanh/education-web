package com.lynhatkhanh.educationweb.educationweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    String username;
    String password;
    String firstName;
    String lastName;
    String address;
    String telephone;
    String gender;
    Boolean enabled;
    String email;
    LocalDate dob;

    @ManyToMany
    Set<Role> roles;

}
