package com.elite.miko.quiz.infrastructure.dao;

import com.elite.miko.quiz.domain.model.Answer;
import java.util.List;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface AnswerDao {

    @Select
    List<Answer> fetchAll();

}
