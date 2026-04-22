package com.college.feedback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.college.feedback.Entity.User;
import com.college.feedback.Repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(UserRepository repo) {
        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");

                repo.save(admin);
            }

            System.out.println("Admin initialized");
        };
    }
}