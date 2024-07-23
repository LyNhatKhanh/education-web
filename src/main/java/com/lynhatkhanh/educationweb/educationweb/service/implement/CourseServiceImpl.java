package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.constant.SystemConstant;
import com.lynhatkhanh.educationweb.educationweb.dao.CourseRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.UserAccountRepository;
import com.lynhatkhanh.educationweb.educationweb.exception.ResourceNotFoundException;
import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.model.CourseUser;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private UserAccountRepository userAccountRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserAccountRepository userAccountRepository) {
        this.courseRepository = courseRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
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
    public void deleteById(int theId) {
        courseRepository.deleteById(theId);
    }

    @Override
    public Page<Course> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, SystemConstant.PAGE_SIZE);

        return courseRepository.findAll(pageable);
    }

    @Override
    public List<Course> searchByKeyword(String keyword) {
        return courseRepository.searchCourse(keyword);
    }

    @Override
    public Page<Course> searchByKeyword(String keyword, Integer pageNo) {
        List<Course> listCourse = courseRepository.searchCourse(keyword);

        Pageable pageable = PageRequest.of(pageNo-1,SystemConstant.PAGE_SIZE);

        // Offset: start at [x] index to the end (of list)
        // limit: after list return, take [x] results of this list

        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listCourse.size() ? listCourse.size() : (pageable.getOffset() + pageable.getPageSize()));

        listCourse = listCourse.subList(start, end);

        return new PageImpl<Course>(listCourse, pageable, courseRepository.searchCourse(keyword).size());
    }

    /*@Override
    public void addUserToCourse(int userId, int courseId) {
        UserAccount userAccount = userAccountRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserAccount not found!"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!"));

        if (userAccount.getUserRole().stream().noneMatch(userRole -> !userRole.getRole().getName().equals("ROLE_STUDENT"))) {
            course.getCourseStudents().add(new CourseUser(course, userAccount));
            userAccount.getEnrolledCourses().add(new CourseUser(course, userAccount));
        } else if (userAccount.getUserRole().stream().noneMatch(userRole -> !userRole.getRole().getName().equals("ROLE_INSTRUCTOR"))) {
            course.setInstructor(userAccount);
            userAccount.getTaughtCourses().add(course);
        }

        courseRepository.save(course);
        userAccountRepository.save(userAccount);
    }*/
}
