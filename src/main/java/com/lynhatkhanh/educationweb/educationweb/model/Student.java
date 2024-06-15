package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends People {

    // =========== define fields ===========
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;


    // =========== define constructors ===========

    public Student() {
    }

    public Student(List<Course> courses) {
        this.courses = courses;
    }

    public Student(String firstName, String lastName, String email, Timestamp createDate, Timestamp modifiedDate, String createBy, String modifiedBy, List<Course> courses) {
        super(firstName, lastName, email, createDate, modifiedDate, createBy, modifiedBy);
        this.courses = courses;
    }

    public Student(String firstName, String lastName, String email, List<Course> courses) {
        super(firstName, lastName, email);
        this.courses = courses;
    }


    // =========== define getters/setters ===========

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    // =========== define toString() ===========


    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "courses=" + courses +
                '}';
    }
}
