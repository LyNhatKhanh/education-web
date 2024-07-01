//package com.lynhatkhanh.educationweb.educationweb.service.didnotUse;
//
//import com.lynhatkhanh.educationweb.educationweb.dao.didnotUse.StudentRepository;
//import com.lynhatkhanh.educationweb.educationweb.model.didnotUse.Student;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class StudentServiceImpl implements StudentService {
//
//    private StudentRepository studentRepository;
//
//    @Autowired
//    public StudentServiceImpl(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @Override
//    @Transactional
//    public Student save(Student theStudent) {
//        return studentRepository.save(theStudent);
//    }
//}
