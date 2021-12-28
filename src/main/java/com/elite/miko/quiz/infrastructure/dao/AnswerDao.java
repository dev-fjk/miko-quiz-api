package com.elite.miko.quiz.infrastructure.dao;

import com.elite.miko.quiz.infrastructure.model.entity.Answer;
import java.util.List;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.lang.NonNull;

@Dao
@ConfigAutowireable
public interface AnswerDao {

    /**
     * クイズIDの一覧と一致する回答一覧を取得する
     *
     * @param quizIdList : クイズIDのリスト
     * @return 回答一覧
     */
    @Select
    List<Answer> fetchByQuizIdList(@NonNull List<Long> quizIdList);

    /**
     * 回答の追加を行う
     *
     * @param answer : 追加する回答情報
     * @return : 追加件数
     */
    @Insert(excludeNull = true)
    int insert(@NonNull Answer answer);
}
