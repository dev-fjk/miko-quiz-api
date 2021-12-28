package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto.AnswerAddDto;
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

    /**
     * 回答の追加を行う
     *
     * @param quizId    : クイズID
     * @param answerAddDto : 回答追加Dto
     * @return 更新成功の場合trueを返却
     */
    boolean insertAnswer(long quizId, AnswerAddDto answerAddDto);
}
