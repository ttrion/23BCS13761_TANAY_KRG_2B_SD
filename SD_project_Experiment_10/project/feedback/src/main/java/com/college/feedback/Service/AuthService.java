package com.college.feedback.Service;

import com.college.feedback.Entity.User;
import com.college.feedback.Repository.UserRepository;
import com.college.feedback.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, String> login(String username, String password) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        Map<String, String> res = new HashMap<>();
        res.put("token", token);
        res.put("role", user.getRole());

        return res;
    }

    public String register(User user) {
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return "User already exists";
        }

        user.setRole("USER");
        user.setSubmitted(false);
        repo.save(user); // FIX: Actually persist the user to the database
        return "User registered successfully";
    }
}