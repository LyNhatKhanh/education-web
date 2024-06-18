package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.model.Instructor;

import java.util.List;

public interface InstructorService {

    Instructor save(Instructor theInstructor);

    List<Instructor> findAll();
}
