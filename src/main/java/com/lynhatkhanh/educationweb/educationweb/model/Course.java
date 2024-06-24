package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    private String title;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor theInstructor;

    @OneToMany(
            mappedBy = "courseId",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Lecture> listLecture;

    // =========== define constructors ===========

    public Course() {
    }

    public Course(String title) {
        this.title = title;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Instructor getTheInstructor() {
        return theInstructor;
    }

    public void setTheInstructor(Instructor theInstructor) {
        this.theInstructor = theInstructor;
    }

    public List<Lecture> getListLecture() {
        return listLecture;
    }

    public void setListLecture(List<Lecture> listLecture) {
        this.listLecture = listLecture;
    }

    // add convenient method for bidirectional relationship
    public void addLecture(Lecture lecture) {
        if (listLecture == null)
            listLecture = new ArrayList<>();
        listLecture.add(lecture);
        lecture.setCourseId(this);
    }

    // =========== define toString() ===========


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
