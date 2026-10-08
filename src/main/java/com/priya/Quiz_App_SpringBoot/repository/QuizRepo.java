package com.priya.Quiz_App_SpringBoot.repository;

import com.priya.Quiz_App_SpringBoot.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
