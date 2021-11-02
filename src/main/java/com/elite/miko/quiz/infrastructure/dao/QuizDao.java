package com.elite.miko.quiz.infrastructure.dao;

import com.elite.miko.quiz.domain.model.entity.Quiz;
import com.elite.miko.quiz.domain.model.entity.QuizJoinAnswer;
import java.util.List;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface QuizDao {

    @Select
    List<Integer> fetchAllQuizIdByStatus(String statusName);

    @Select
    List<QuizJoinAnswer> fetchQuizJoinAnswers(List<Integer> idList, int count);
}
