package com.elite.miko.quiz.domain.service;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import java.util.List;

/**
 * Objectを返しているところは実際は別のクラスを返す
 */
public interface QuizAdminService {

    Object fetchQuiz(int start, int count);

    Object fetchRequestQuiz(int start, int count);

    void insertQuiz(QuizAddDto quizAddDto);

    void updateQuiz(QuizUpdateDto quizUpdateDto);

    void deleteQuiz(List<Long> quizIdList);
}
