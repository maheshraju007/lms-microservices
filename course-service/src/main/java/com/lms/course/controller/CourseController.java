package com.lms.course.controller;

import com.lms.course.dto.CourseRequest;
import com.lms.course.dto.LessonRequest;
import com.lms.course.model.Course;
import com.lms.course.model.Lesson;
import com.lms.course.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/student/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/student/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/student/courses/{id}/lessons")
    public ResponseEntity<List<Lesson>> getCourseLessons(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getLessonsByCourseId(id));
    }

    @PostMapping("/student/courses/{id}/enroll")
    public ResponseEntity<Course> enrollCourse(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(courseService.enrollStudent(id, auth.getName()));
    }

    @GetMapping("/student/my-courses")
    public ResponseEntity<List<Course>> getMyCourses(Authentication auth) {
        return ResponseEntity.ok(courseService.getStudentCourses(auth.getName()));
    }

    @PostMapping("/instructor/courses")
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequest request,
                                                Authentication auth,
                                                @RequestHeader("X-User-FirstName") String firstName,
                                                @RequestHeader("X-User-LastName") String lastName) {
        return ResponseEntity.ok(courseService.createCourse(request, auth.getName(), firstName, lastName));
    }

    @GetMapping("/instructor/my-courses")
    public ResponseEntity<List<Course>> getInstructorCourses(Authentication auth) {
        return ResponseEntity.ok(courseService.getInstructorCourses(auth.getName()));
    }

    @PostMapping("/instructor/courses/{courseId}/lessons")
    public ResponseEntity<Lesson> addLesson(@PathVariable Long courseId,
                                             @RequestBody LessonRequest request) {
        return ResponseEntity.ok(courseService.addLesson(courseId, request));
    }
}
