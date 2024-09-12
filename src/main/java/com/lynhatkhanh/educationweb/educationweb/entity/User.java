package com.lynhatkhanh.educationweb.educationweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
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

    @OneToMany(mappedBy = "instructor")
    List<Course> taughtCourses;

    @ManyToMany
    @JoinTable(
            name = "user_courses", // Join table name
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key column in join table for this entity
            inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key column in join table for the other entity
    )
    Set<Course> enrolledCourses;

}
