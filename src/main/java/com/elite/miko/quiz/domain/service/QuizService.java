package com.elite.miko.quiz.domain.service;

import com.elite.miko.quiz.domain.model.dto.ResultFetchQuizInfo;
import com.elite.miko.quiz.presantation.model.form.QuizRequestForm;
import java.util.List;

public interface QuizService {

    List<ResultFetchQuizInfo> fetchQuiz(int count);

    int addQuizRequest(QuizRequestForm quizRequestForm);

}
