package com.lynhatkhanh.educationweb.educationweb.dto.response;

import com.lynhatkhanh.educationweb.educationweb.entity.Course;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LectureResponse {
    String title;
    String content;
    Boolean isEnabled;
    Course courseId;
}
