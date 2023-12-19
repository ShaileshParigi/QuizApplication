package com.telusko.QuizApplication.dao;

import com.telusko.QuizApplication.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
