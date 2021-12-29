package com.elite.miko.quiz.domain.service;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.model.result.QuizManageResult;
import java.util.List;

/**
 * Objectを返しているところは実際は別のクラスを返す
 */
public interface QuizAdminService {

    /**
     * クイズ一覧の取得を行う
     *
     * @param start : offset 取得開始
     * @param count : limit 取得件数
     * @return 取得結果
     */
    QuizManageResult fetchQuiz(int start, int count);

    /**
     * リクエスト中のクイズ一覧の取得を行う
     *
     * @param start : offset 取得開始
     * @param count : limit 取得件数
     * @return 取得結果
     */
    QuizManageResult fetchRequestQuiz(int start, int count);

    /**
     * クイズの追加を行う
     *
     * @param quizAddDto : クイズ追加Dao
     */
    void insertQuiz(QuizAddDto quizAddDto);

    void updateQuiz(QuizUpdateDto quizUpdateDto);

    void deleteQuiz(List<Long> quizIdList);
}
