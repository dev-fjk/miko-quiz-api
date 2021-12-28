package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    @Override
    public Object fetchQuiz(int start, int count) {
        return null;
    }

    @Override
    public Object fetchRequestQuiz(int start, int count) {
        return null;
    }

    @Override
    public void insertQuiz(QuizAddDto quizAddDto) {

    }

    @Override
    public void updateQuiz(QuizUpdateDto quizUpdateDto) {

    }

    @Override
    public void deleteQuiz(List<Long> quizIdList) {

    }
}
