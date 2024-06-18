package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.CourseRepository;
import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    @Transactional
    public Course save(Course theCourse) {
        return courseRepository.save(theCourse);
    }

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result = courseRepository.findById(theId);

        Course theCourse = null;
        if(result.isPresent())
            theCourse = result.get();
        else
            throw new RuntimeException("Course id not found - " + theId);

        return theCourse;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        courseRepository.deleteById(theId);
    }
}
