package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.Lecture;

import java.util.List;

public interface LectureService {

    List<Lecture> findAll();

    Lecture findById(int theId);

    Lecture save(Lecture lecture);


}
