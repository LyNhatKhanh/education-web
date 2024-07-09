package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>,
        PagingAndSortingRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE c.title LIKE %?1%")
        // %?1% - RequestParam (1 param) - %: any-word before and after
    List<Course> searchCourse(String keyword);

}
