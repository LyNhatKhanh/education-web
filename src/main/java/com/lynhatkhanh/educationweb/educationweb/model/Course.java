package com.lynhatkhanh.educationweb.educationweb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    // =========== define fields ===========

    @Column(name = "title")
    @NotBlank(message = "Title is required")
    private String title;

    @OneToMany(
            mappedBy = "courseId",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    @JsonManagedReference
    private List<Lecture> listLecture;

    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<CourseUser> courseStudents;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private UserAccount instructor;

    // =========== define constructors ===========

    public Course() {
    }

    public Course(String title, List<Lecture> listLecture, Set<CourseUser> courseStudents, UserAccount instructor) {
        this.title = title;
        this.listLecture = listLecture;
        this.courseStudents = courseStudents;
        this.instructor = instructor;
    }

    public Course(Date createdDate, Date modifiedDate, String createdBy, String modifiedBy, String title, List<Lecture> listLecture, Set<CourseUser> courseStudents, UserAccount instructor) {
        super(createdDate, modifiedDate, createdBy, modifiedBy);
        this.title = title;
        this.listLecture = listLecture;
        this.courseStudents = courseStudents;
        this.instructor = instructor;
    }

    // =========== define getters/setters ===========


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Lecture> getListLecture() {
        return listLecture;
    }

    public void setListLecture(List<Lecture> listLecture) {
        this.listLecture = listLecture;
    }

    public Set<CourseUser> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Set<CourseUser> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public UserAccount getInstructor() {
        return instructor;
    }

    public void setInstructor(UserAccount instructors) {
        this.instructor = instructors;
    }

    // =========== define toString() ===========


}
