package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.CourseRepository;
import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Course> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,10);

        return courseRepository.findAll(pageable);
    }

    @Override
    public List<Course> searchCourse(String keyword) {
        return courseRepository.searchCourse(keyword);
    }

    @Override
    public Page<Course> searchCourse(String keyword, Integer pageNo) {
        List<Course> listCourse = courseRepository.searchCourse(keyword);

        Pageable pageable = PageRequest.of(pageNo-1,10);

        long  start = pageable.getOffset();
        long end = (pageable.getOffset() + pageable.getPageSize()) > listCourse.size() ? listCourse.size() : (pageable.getOffset() + pageable.getPageSize());

        listCourse = listCourse.subList((int) start, (int) end);

        return new PageImpl<Course>(listCourse, pageable, listCourse.size());
    }
}
