package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.consts.QuizStatus;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.model.result.FetchQuizResult;
import com.elite.miko.quiz.domain.model.result.QuizManageResult;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;

public interface QuizRepository {

    /**
     * クイズの問題をランダムで指定件数文取得する
     *
     * @param count : 指定取得件数
     * @return クイズ取得結果
     */
    FetchQuizResult fetchRandomQuiz(int count);

    /**
     * クイズ一覧の取得を行う
     *
     * @param start : offset 取得開始
     * @param count : limit 取得件数
     * @return クイズ取得結果
     */
    FetchQuizResult fetchQuiz(int start, int count);

    /**
     * クイズ一覧の取得を行う
     *
     * @param start  : offset 取得開始
     * @param count  : limit 取得件数
     * @param status : 取得条件に指定するクイズの状態
     * @return クイズ取得結果
     */
    FetchQuizResult fetchQuiz(int start, int count, QuizStatus status);

    /**
     * クイズの追加を行う
     *
     * @param quizAddDto : クイズ追加Dto
     * @return 更新成功の場合trueを返却
     */
    Quiz insertQuiz(QuizAddDto quizAddDto);

    void updateQuiz(QuizUpdateDto quizUpdateDto);

    void deleteQuiz(List<Long> quizIdList);
}
