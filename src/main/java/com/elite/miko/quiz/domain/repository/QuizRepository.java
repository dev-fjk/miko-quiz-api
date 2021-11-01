package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.dto.QuizStatusType;
import com.elite.miko.quiz.domain.model.entity.Quiz;
import java.util.List;

public interface QuizRepository {

    List<Quiz> fetchAll();

    List<Integer> fetchAllQuizIdByStatus(QuizStatusType statusType);

}
