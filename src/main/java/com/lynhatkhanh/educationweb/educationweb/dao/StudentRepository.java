package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
