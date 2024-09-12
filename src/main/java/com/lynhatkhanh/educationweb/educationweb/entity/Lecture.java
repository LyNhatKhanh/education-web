package com.lynhatkhanh.educationweb.educationweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "lecture")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecture extends BaseEntity {
    String title;
    String content;
    @Column(name = "enabled")
    Boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course courseId;
}
