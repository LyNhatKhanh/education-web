package com.lynhatkhanh.educationweb.educationweb.service.exclude.implement;

import com.lynhatkhanh.educationweb.educationweb.constant.SystemConstant;
import com.lynhatkhanh.educationweb.educationweb.dao.exclude.CourseRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.exclude.UserAccountRepository;
import com.lynhatkhanh.educationweb.educationweb.model.Course;
import com.lynhatkhanh.educationweb.educationweb.model.CourseUser;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.service.exclude.CourseService;
import com.lynhatkhanh.educationweb.educationweb.utils.PageableUtil;
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

    private UserAccountRepository userAccountRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserAccountRepository userAccountRepository) {
        this.courseRepository = courseRepository;
        this.userAccountRepository = userAccountRepository;
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

        PageableUtil<Course> pageableUtil = new PageableUtil<>(listCourse, pageNo);
        List<Course> showList = pageableUtil.getShowList();

        return new PageImpl<>(showList, pageableUtil.getPageable(), listCourse.size());
    }

}
