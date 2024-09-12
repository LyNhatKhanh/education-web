package com.lynhatkhanh.educationweb.educationweb.dto.response;

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
public class CourseResponse {
    String title;
    Boolean isEnabled;
    Set<UserResponse> enrolledUsers;
    User instructor;
    /*TODO: LectureResponse*/
    List<Lecture> lectureList;
}
