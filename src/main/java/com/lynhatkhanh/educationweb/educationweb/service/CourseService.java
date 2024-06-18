package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.Course;

import java.util.List;

public interface CourseService {

    Course save(Course theCourse);

    List<Course> findAll();

    Course findById(int theId);

    void deleteById(int theId);

}
