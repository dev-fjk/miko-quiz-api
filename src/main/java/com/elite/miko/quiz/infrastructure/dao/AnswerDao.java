package com.elite.miko.quiz.infrastructure.dao;

import com.elite.miko.quiz.domain.model.entity.Answer;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface AnswerDao {

    @Insert(excludeNull = true,queryTimeout = 10)
    int createAnswer(Answer answer);
}
