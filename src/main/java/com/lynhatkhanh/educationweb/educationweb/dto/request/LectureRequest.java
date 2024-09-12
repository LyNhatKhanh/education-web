package com.lynhatkhanh.educationweb.educationweb.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LectureRequest {
    String title;
    String content;
    Boolean isEnabled;
    String courseId;
}
