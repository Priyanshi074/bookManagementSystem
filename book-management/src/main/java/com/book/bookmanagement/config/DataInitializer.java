package com.book.bookmanagement.config;

import com.book.bookmanagement.model.User;
import com.book.bookmanagement.model.UserRole;
import com.book.bookmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(UserRole.ROLE_ADMIN);
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("student").isEmpty()) {
                User student = new User();
                student.setUsername("student");
                student.setEmail("student@example.com");
                student.setPassword(passwordEncoder.encode("student123"));
                student.setRole(UserRole.ROLE_STUDENT);
                userRepository.save(student);
            }

            System.out.println("Sample users inserted.");
        };
    }
}
