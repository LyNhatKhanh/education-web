package com.lynhatkhanh.educationweb.educationweb.dao.exclude;

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

    @Query("SELECT l FROM Lecture AS l WHERE l.title LIKE %?1%")
    List<Lecture> searchLecturesByKeyword(String keyword);

    @Query("SELECT l FROM Lecture AS l WHERE l.courseId.id = :courseId")
    List<Lecture> findLectureOfCourse(@Param("courseId") int courseId);

    @Query("SELECT l FROM Lecture AS l WHERE l.courseId IS NULL")
    List<Lecture> findLectureWithoutCourse();

    @Query("SELECT l FROM Lecture AS l " +
            "WHERE l.courseId.id = :courseId AND l.title LIKE %:keyword%")
    List<Lecture> searchLecturesOfCourseByKeyword(@Param("keyword") String keyword, @Param("courseId") int courseId);

    @Query("SELECT l FROM Lecture AS l " +
            "WHERE l.courseId IS NULL AND l.title LIKE %:keyword%")
    List<Lecture> searchLecturesWithoutCourseByKeyword(@Param("keyword") String keyword);
}
