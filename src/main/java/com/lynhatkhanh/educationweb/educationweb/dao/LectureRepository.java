package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.Lecture;
import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Query("SELECT l FROM Lecture l WHERE l.title LIKE %?1%")
    List<Lecture> searchLecturesByKeyword(String keyword);

    @Query("SELECT l FROM Lecture l WHERE l.courseId.id = :courseId")
    List<Lecture> findLectureOfCourse(@Param("courseId") int courseId);

    @Query("SELECT l FROM Lecture l WHERE l.courseId IS NULL")
    List<Lecture> findLectureWithoutCourse();

}
