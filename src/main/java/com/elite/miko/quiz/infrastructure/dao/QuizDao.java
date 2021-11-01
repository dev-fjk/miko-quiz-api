package com.elite.miko.quiz.infrastructure.dao;

import com.elite.miko.quiz.domain.model.entity.Quiz;
import java.util.List;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface QuizDao {

    @Select
    List<Quiz> fetchAll();

    @Select
    List<Integer> fetchAllApplyQuizId();
}
