package com.college.feedback.Controller;

import com.college.feedback.Service.AuthService;
import com.college.feedback.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService service;

    // LOGIN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {
        return service.login(req.get("username"), req.get("password"));
    }

    // REGISTER (NEW)
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }
}