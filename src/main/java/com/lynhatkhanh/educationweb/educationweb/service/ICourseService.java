package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.CourseRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.CourseResponse;

import java.util.List;

public interface ICourseService {
    CourseResponse getCourse(String name);
    List<CourseResponse> getCourses();
    CourseResponse createCourse(CourseRequest request);
    CourseResponse updateCourse(String name, CourseRequest request);
    void deleteCourse(String name);

}
