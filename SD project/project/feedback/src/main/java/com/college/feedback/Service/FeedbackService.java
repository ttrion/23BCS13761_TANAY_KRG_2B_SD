package com.college.feedback.Service;

import com.college.feedback.Entity.Feedback;
import com.college.feedback.Entity.User;
import com.college.feedback.Repository.FeedbackRepository;
import com.college.feedback.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repo;

    @Autowired
    private UserRepository userRepo;

    public boolean hasSubmitted(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.isSubmitted();
    }

    public void submit(String username, Feedback f) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isSubmitted()) {
            throw new RuntimeException("Already submitted");
        }

        f.setUser(user);
        repo.save(f);

        user.setSubmitted(true);
        userRepo.save(user);
    }

    public Map<String, Double> average() {
        List<Feedback> list = repo.findAll();
        Map<String, Double> map = new HashMap<>();

        if (list.isEmpty()) {
            map.put("SD", 0.0);
            map.put("FS", 0.0);
            map.put("NM", 0.0);
            map.put("SS", 0.0);
            map.put("Aptitude", 0.0);
            map.put("Cloud", 0.0);
            return map;
        }

        map.put("SD", list.stream().mapToInt(Feedback::getSd).average().orElse(0));
        map.put("FS", list.stream().mapToInt(Feedback::getFs).average().orElse(0));
        map.put("NM", list.stream().mapToInt(Feedback::getNm).average().orElse(0));
        map.put("SS", list.stream().mapToInt(Feedback::getSs).average().orElse(0));
        map.put("Aptitude", list.stream().mapToInt(Feedback::getAptitude).average().orElse(0));
        map.put("Cloud", list.stream().mapToInt(Feedback::getCloud).average().orElse(0));

        return map;
    }
}