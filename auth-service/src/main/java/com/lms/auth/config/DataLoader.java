package com.lms.auth.config;

import com.lms.auth.model.User;
import com.lms.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User instructor = new User();
                instructor.setEmail("instructor@test.com");
                instructor.setPassword(passwordEncoder.encode("password123"));
                instructor.setFirstName("John");
                instructor.setLastName("Smith");
                instructor.setRole(User.Role.INSTRUCTOR);
                userRepository.save(instructor);

                User instructor2 = new User();
                instructor2.setEmail("sarah@test.com");
                instructor2.setPassword(passwordEncoder.encode("password123"));
                instructor2.setFirstName("Sarah");
                instructor2.setLastName("Johnson");
                instructor2.setRole(User.Role.INSTRUCTOR);
                userRepository.save(instructor2);

                User student = new User();
                student.setEmail("student@test.com");
                student.setPassword(passwordEncoder.encode("password123"));
                student.setFirstName("Mike");
                student.setLastName("Davis");
                student.setRole(User.Role.STUDENT);
                userRepository.save(student);

                System.out.println("✅ Auth Service: 3 test users created");
            }
        };
    }
}
