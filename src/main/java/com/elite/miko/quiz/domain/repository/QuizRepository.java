package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import java.util.List;

public interface QuizRepository {

    Object fetchQuiz(int start, int count);

    Object fetchRequestQuiz(int start, int count);

    void insertQuiz(QuizAddDto quizAddDto);

    void updateQuiz(QuizUpdateDto quizUpdateDto);

    void deleteQuiz(List<Long> quizIdList);
}
