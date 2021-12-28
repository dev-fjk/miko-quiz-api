package com.elite.miko.quiz.infrastructure.dao;

import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

@Dao
@ConfigAutowireable
public interface QuizDao {

    @Select
    List<Quiz> fetchAll();

}
