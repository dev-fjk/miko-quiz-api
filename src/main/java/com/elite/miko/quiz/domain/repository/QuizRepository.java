package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;

public interface QuizRepository {

    /**
     * クイズの問題をランダムで指定件数文取得する
     *
     * @param count : 指定取得件数
     * @return クイズ一覧
     */
    List<Quiz> fetchRandomQuiz(int count);

    Object fetchQuiz(int start, int count);

    Object fetchRequestQuiz(int start, int count);

    void insertQuiz(QuizAddDto quizAddDto);

    void updateQuiz(QuizUpdateDto quizUpdateDto);

    void deleteQuiz(List<Long> quizIdList);
}
