package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.CourseRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.LectureRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.UserRepository;
import com.lynhatkhanh.educationweb.educationweb.dto.request.CourseRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.CourseResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.Course;
import com.lynhatkhanh.educationweb.educationweb.entity.Lecture;
import com.lynhatkhanh.educationweb.educationweb.entity.User;
import com.lynhatkhanh.educationweb.educationweb.exception.AppException;
import com.lynhatkhanh.educationweb.educationweb.exception.ErrorCode;
import com.lynhatkhanh.educationweb.educationweb.mapper.CourseMapper;
import com.lynhatkhanh.educationweb.educationweb.service.ICourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseServiceImpl implements ICourseService {

    CourseRepository courseRepository;
    UserRepository userRepository;
    LectureRepository lectureRepository;
    CourseMapper courseMapper;

    @Override
    public CourseResponse getCourse(String name) {
        Course course = courseRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXISTED));
        return courseMapper.toCourseResponse(course);
    }

    @Override
    public List<CourseResponse> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> courseMapper.toCourseResponse(course))
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse createCourse(CourseRequest request) {
        if (courseRepository.existsByTitle(request.getTitle()))
            throw new AppException(ErrorCode.COURSE_EXISTED);

        Course course = courseMapper.toCourse(request);
        if (request.getEnrolledUsers() != null) {
            Set<User> userList = new HashSet<>();
            userRepository.findAllById(request.getEnrolledUsers()).forEach(userList::add);
            course.setEnrolledUsers(userList);
        }
        if (request.getLectureList() != null) {
            List<Lecture> lectureList = new ArrayList<>();
            lectureRepository.findAllById(request.getLectureList()).forEach(lectureList::add);
            course.setLectureList(lectureList);
        }
        course = courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    @Override
    public CourseResponse updateCourse(String name, CourseRequest request) {
        if (courseRepository.existsByTitle(request.getTitle()))
            throw new AppException(ErrorCode.COURSE_EXISTED);

        Course course = courseRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXISTED));
        courseMapper.updateCourse(course, request);
        if (request.getEnrolledUsers() != null) {
            Set<User> userList = new HashSet<>();
            userRepository.findAllById(request.getEnrolledUsers()).forEach(userList::add);
            course.setEnrolledUsers(userList);
        }
        if (request.getLectureList() != null) {
            List<Lecture> lectureList = new ArrayList<>();
            lectureRepository.findAllById(request.getLectureList()).forEach(lectureList::add);
            course.setLectureList(lectureList);
        }
        course = courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    @Override
    public void deleteCourse(String name) {
        courseRepository.deleteById(name);
    }
}
