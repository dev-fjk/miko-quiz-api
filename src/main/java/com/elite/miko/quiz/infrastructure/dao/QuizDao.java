package com.elite.miko.quiz.infrastructure.dao;

import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.lang.NonNull;

@Dao
@ConfigAutowireable
public interface QuizDao {

    /**
     * クイズの問題をランダムで指定件数文取得する
     *
     * @param options : 取得オプション
     * @return クイズ一覧
     */
    @Select
    List<Quiz> fetchQuizRandom(SelectOptions options);

    /**
     * クイズの追加を行う
     *
     * @param quiz : 追加するQuiz情報
     * @return : 追加件数
     */
    @Insert(excludeNull = true)
    int insert(@NonNull Quiz quiz);
}
