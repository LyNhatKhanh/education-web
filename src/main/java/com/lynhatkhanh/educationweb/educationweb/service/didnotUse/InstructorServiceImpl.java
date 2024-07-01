//package com.lynhatkhanh.educationweb.educationweb.service.didnotUse;
//
//import com.lynhatkhanh.educationweb.educationweb.dao.didnotUse.InstructorRepository;
//import com.lynhatkhanh.educationweb.educationweb.model.didnotUse.Instructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class InstructorServiceImpl implements InstructorService {
//
//    private InstructorRepository instructorRepository;
//
//    @Autowired
//    public InstructorServiceImpl(InstructorRepository instructorRepository) {
//        this.instructorRepository = instructorRepository;
//    }
//
//    @Override
//    public Instructor save(Instructor theInstructor) {
//        return instructorRepository.save(theInstructor);
//    }
//
//    @Override
//    public List<Instructor> findAll() {
//        return (List<Instructor>) instructorRepository.findAll();
//    }
//}
