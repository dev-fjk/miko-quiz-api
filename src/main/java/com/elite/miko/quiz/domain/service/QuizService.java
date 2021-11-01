package com.elite.miko.quiz.domain.service;

import com.elite.miko.quiz.domain.model.dto.ResultFetchQuizInfo;
import com.elite.miko.quiz.domain.model.entity.Quiz;
import java.util.List;

public interface QuizService {

    List<Quiz> fetchAll();

    List<ResultFetchQuizInfo> fetchQuiz(int count);
}
