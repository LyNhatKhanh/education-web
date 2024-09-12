package com.lynhatkhanh.educationweb.educationweb.mapper;

import com.lynhatkhanh.educationweb.educationweb.dto.request.CourseRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.CourseResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "enrolledUsers", ignore = true)
    @Mapping(target = "lectureList", ignore = true)
    Course toCourse(CourseRequest request);

    CourseResponse toCourseResponse(Course course);

    @Mapping(target = "enrolledUsers", ignore = true)
    @Mapping(target = "lectureList", ignore = true)
    void updateCourse(@MappingTarget Course course, CourseRequest request);
}
