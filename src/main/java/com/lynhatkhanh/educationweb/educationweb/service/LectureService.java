package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.Lecture;
import org.springframework.data.domain.Page;

public interface LectureService extends GenericService<Lecture> {

    Page<Lecture> findLectureOfCourse(Integer pageNo, int courseId);

    Page<Lecture> findLectureWithoutCourse(Integer pageNo);

    Page<Lecture> searchLectureOfCourse(String keyword, Integer pageNo, int courseId);
}
