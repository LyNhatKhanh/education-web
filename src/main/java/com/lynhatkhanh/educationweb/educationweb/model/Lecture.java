package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "lecture")
public class Lecture extends BaseEntity {

    // define fields

    @Column(name = "title")
    @NotBlank(message = "Title is required!")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    private Course courseId;

    // define constructor

    public Lecture() {
    }

    public Lecture(String title, String content, Course courseId) {
        this.title = title;
        this.content = content;
        this.courseId = courseId;
    }

    public Lecture(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Lecture(Date createdDate, Date modifiedDate, String createdBy, String modifiedBy, String title, String content, Course courseId) {
        super(createdDate, modifiedDate, createdBy, modifiedBy);
        this.title = title;
        this.content = content;
        this.courseId = courseId;
    }

    // define getters / setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }


    // define toString()

    @Override
    public String toString() {
        return "Lecture{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
