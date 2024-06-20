package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    Course save(Course theCourse);

    List<Course> findAll();

    Course findById(int theId);

    void deleteById(int theId);

    Page<Course> getAll(Integer pageNo);

    List<Course> searchCourse(String keyword);

    Page<Course> searchCourse(String keyword, Integer pageNo);
}
