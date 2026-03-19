package com.lms.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String instructorEmail;
    private String instructorFirstName;
    private String instructorLastName;
    private String category;
    private String imageUrl;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(name = "course_enrollments", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "student_email")
    private Set<String> enrolledStudentEmails = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("orderIndex ASC")
    private List<Lesson> lessons = new ArrayList<>();

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getInstructorEmail() { return instructorEmail; }
    public void setInstructorEmail(String instructorEmail) { this.instructorEmail = instructorEmail; }
    public String getInstructorFirstName() { return instructorFirstName; }
    public void setInstructorFirstName(String instructorFirstName) { this.instructorFirstName = instructorFirstName; }
    public String getInstructorLastName() { return instructorLastName; }
    public void setInstructorLastName(String instructorLastName) { this.instructorLastName = instructorLastName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Set<String> getEnrolledStudentEmails() { return enrolledStudentEmails; }
    public void setEnrolledStudentEmails(Set<String> enrolledStudentEmails) { this.enrolledStudentEmails = enrolledStudentEmails; }
    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }
}
