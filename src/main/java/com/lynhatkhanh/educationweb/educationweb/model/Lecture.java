package com.lynhatkhanh.educationweb.educationweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lecture")
public class Lecture {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "title")
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

    // define getters / setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
