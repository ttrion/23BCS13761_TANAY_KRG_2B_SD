package com.college.feedback.Controller;

import com.college.feedback.Entity.Feedback;
import com.college.feedback.Entity.User;
import com.college.feedback.Repository.UserRepository;
import com.college.feedback.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/status")
    public boolean status(Authentication auth) {
        if (auth == null) return false;
        return service.hasSubmitted(auth.getName());
    }

    @PostMapping
    public void submit(@RequestBody Feedback f, Authentication auth) {

        if (auth == null) {
            throw new RuntimeException("Not authenticated");
        }

        service.submit(auth.getName(), f);
    }

    @GetMapping("/average")
    public Map<String, Double> average(Authentication auth) {

        if (auth == null) {
            throw new RuntimeException("Not authenticated");
        }

        User user = userRepo.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!"ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Forbidden");
        }

        return service.average();
    }
}