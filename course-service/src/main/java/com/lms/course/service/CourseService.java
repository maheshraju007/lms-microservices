package com.lms.course.service;

import com.lms.course.dto.CourseRequest;
import com.lms.course.dto.LessonRequest;
import com.lms.course.model.Course;
import com.lms.course.model.Lesson;
import com.lms.course.repository.CourseRepository;
import com.lms.course.repository.LessonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CourseService(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    public Course createCourse(CourseRequest request, String instructorEmail,
                                String firstName, String lastName) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCategory(request.getCategory());
        course.setImageUrl(request.getImageUrl());
        course.setInstructorEmail(instructorEmail);
        course.setInstructorFirstName(firstName);
        course.setInstructorLastName(lastName);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course enrollStudent(Long courseId, String studentEmail) {
        Course course = getCourseById(courseId);
        course.getEnrolledStudentEmails().add(studentEmail);
        return courseRepository.save(course);
    }

    public List<Course> getInstructorCourses(String email) {
        return courseRepository.findByInstructorEmail(email);
    }

    public List<Course> getStudentCourses(String email) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getEnrolledStudentEmails().contains(email))
                .toList();
    }

    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findByCourseIdOrderByOrderIndex(courseId);
    }

    public Lesson addLesson(Long courseId, LessonRequest request) {
        Course course = getCourseById(courseId);
        Lesson lesson = new Lesson();
        lesson.setTitle(request.getTitle());
        lesson.setContent(request.getContent());
        lesson.setOrderIndex(request.getOrderIndex());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }
}
