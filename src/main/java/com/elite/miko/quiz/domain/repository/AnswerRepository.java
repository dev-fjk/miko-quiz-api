package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.infrastructure.model.entity.Answer;
import java.util.List;

public interface AnswerRepository {

    /**
     * クイズIDの一覧と一致する回答一覧を取得する
     *
     * @param quizIdList : クイズIDのリスト
     * @return 回答一覧
     */
    List<Answer> fetchByQuizIdList(List<Long> quizIdList);
}
