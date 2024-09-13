package com.lynhatkhanh.educationweb.educationweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course extends BaseEntity {
    @Column(name = "title", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String title;

    @Column(name = "enabled")
    Boolean isEnabled;

    @ManyToMany(mappedBy = "enrolledCourses")
    Set<User> enrolledUsers;

    @ManyToOne
    @JoinColumn(name = "instructor_id") // Foreign key column
    User instructor;

    @OneToMany(mappedBy = "courseId")
    List<Lecture> lectureList;
}
