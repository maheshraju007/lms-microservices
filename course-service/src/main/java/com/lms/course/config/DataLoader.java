package com.lms.course.config;

import com.lms.course.model.Course;
import com.lms.course.model.Lesson;
import com.lms.course.repository.CourseRepository;
import com.lms.course.repository.LessonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initCourses(CourseRepository courseRepository, LessonRepository lessonRepository) {
        return args -> {
            if (courseRepository.count() == 0) {
                createCourse(courseRepository, lessonRepository,
                        "Java Programming Masterclass",
                        "Learn Java from basics to advanced concepts.",
                        "Programming", "instructor@test.com", "John", "Smith",
                        new String[]{"Introduction to Java", "Variables and Data Types", "OOP Concepts", "Collections Framework", "Java Streams API"},
                        new String[]{"Welcome to Java! JDK, JRE, and Hello World.", "Primitive types and type casting.", "Classes, objects, inheritance, polymorphism.", "ArrayList, HashMap, HashSet.", "Streams, lambdas, method references."});

                createCourse(courseRepository, lessonRepository,
                        "Web Development with React",
                        "Build modern web applications with React.",
                        "Web Development", "instructor@test.com", "John", "Smith",
                        new String[]{"React Fundamentals", "State and Hooks", "React Router", "Context API"},
                        new String[]{"JSX, components, props, virtual DOM.", "useState, useEffect, custom hooks.", "Client-side routing with React Router.", "Share state without prop drilling."});

                createCourse(courseRepository, lessonRepository,
                        "Python for Data Science",
                        "Master Python for data analysis.",
                        "Data Science", "sarah@test.com", "Sarah", "Johnson",
                        new String[]{"Python Basics", "NumPy Arrays", "Pandas DataFrames", "Data Visualization", "Machine Learning Intro"},
                        new String[]{"Variables, loops, functions.", "Arrays, matrices, math ops.", "Data manipulation and aggregation.", "Charts with Matplotlib.", "First ML model with scikit-learn."});

                createCourse(courseRepository, lessonRepository,
                        "Spring Boot Microservices",
                        "Build scalable microservices with Spring Boot.",
                        "Backend Development", "instructor@test.com", "John", "Smith",
                        new String[]{"Spring Boot Basics", "JPA and Databases", "Security with JWT", "Microservices Architecture"},
                        new String[]{"First Spring Boot app.", "Spring Data JPA.", "Auth and authorization.", "Design microservices."});

                System.out.println("✅ Course Service: 4 courses with lessons created");
            }
        };
    }

    private void createCourse(CourseRepository courseRepo, LessonRepository lessonRepo,
                               String title, String desc, String category,
                               String instructorEmail, String firstName, String lastName,
                               String[] lessonTitles, String[] lessonContents) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(desc);
        course.setCategory(category);
        course.setInstructorEmail(instructorEmail);
        course.setInstructorFirstName(firstName);
        course.setInstructorLastName(lastName);
        courseRepo.save(course);

        for (int i = 0; i < lessonTitles.length; i++) {
            Lesson lesson = new Lesson();
            lesson.setTitle(lessonTitles[i]);
            lesson.setContent(lessonContents[i]);
            lesson.setOrderIndex(i + 1);
            lesson.setCourse(course);
            lessonRepo.save(lesson);
        }
    }
}
