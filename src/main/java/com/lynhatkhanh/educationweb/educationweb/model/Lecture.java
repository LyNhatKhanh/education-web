package com.lynhatkhanh.educationweb.educationweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course courseId;

    // define constructor

    public Lecture() {
    }

    public Lecture(Date createdDate, Date modifiedDate, String createdBy, String modifiedBy, String title, String content, boolean enabled, Course courseId) {
        super(createdDate, modifiedDate, createdBy, modifiedBy);
        this.title = title;
        this.content = content;
        this.enabled = enabled;
        this.courseId = courseId;
    }

    public Lecture(String title, String content, boolean enabled, Course courseId) {
        this.title = title;
        this.content = content;
        this.enabled = enabled;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    // define toString()


}
