package com.elite.miko.quiz.domain.service;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;

public interface QuizClientService {

    Object fetchQuiz(int count);

    void quizRequest(QuizAddDto quizAddDto);
}
