package com.lynhatkhanh.educationweb.educationweb.dto.request;

import com.lynhatkhanh.educationweb.educationweb.entity.Lecture;
import com.lynhatkhanh.educationweb.educationweb.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {
    String title;
    Boolean isEnabled;
    User instructor;
    Set<String> enrolledUsers;
    List<String> lectureList;
}
