package com.elite.miko.quiz.domain.service;

import com.elite.miko.quiz.domain.model.entity.Quiz;
import java.util.List;

public interface QuizService {

    List<Quiz> fetchAll();
}
