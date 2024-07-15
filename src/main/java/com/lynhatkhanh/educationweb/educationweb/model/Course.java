package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    // =========== define fields ===========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "is required")
    private int id;

    @Column(name = "title")
    @NotBlank(message = "Title is required")
    private String title;

    @OneToMany(
            mappedBy = "courseId",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
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

    public Course(String title) {
        this.title = title;
    }

    public Course(String title, List<Lecture> listLecture, Set<CourseUser> courseStudents, UserAccount instructors) {
        this.title = title;
        this.listLecture = listLecture;
        this.courseStudents = courseStudents;
        this.instructor = instructors;
    }

    // =========== define getters/setters ===========

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", listLecture=" + listLecture +
                ", courseStudents=" + courseStudents +
                ", instructors=" + instructor +
                '}';
    }
}
