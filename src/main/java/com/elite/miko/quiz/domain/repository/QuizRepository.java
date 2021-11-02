package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.dto.QuizStatusType;
import com.elite.miko.quiz.domain.model.entity.Quiz;
import com.elite.miko.quiz.domain.model.entity.QuizJoinAnswer;
import java.util.List;

public interface QuizRepository {

    List<Integer> fetchAllQuizIdByStatus(QuizStatusType statusType);

    List<QuizJoinAnswer> fetchQuizData(List<Integer> idList, int count);

}
