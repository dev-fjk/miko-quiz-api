package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.service.QuizAdminService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuizAdminServiceImpl implements QuizAdminService {

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
