package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.dto.QuizStatusType;
import com.elite.miko.quiz.domain.model.entity.Quiz;
import com.elite.miko.quiz.domain.model.entity.QuizJoinAnswer;
import java.util.List;
import org.springframework.lang.NonNull;

public interface QuizRepository {

    List<Integer> fetchAllQuizIdByStatus(QuizStatusType statusType);

    List<QuizJoinAnswer> fetchQuizData(List<Integer> idList, int count);

    int createQuizRequest(@NonNull Quiz quiz);

}
