package com.college.feedback.Repository;

import com.college.feedback.Entity.Feedback;
import com.college.feedback.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByUser(User user);
}