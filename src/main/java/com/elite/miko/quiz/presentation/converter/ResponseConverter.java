package com.elite.miko.quiz.presentation.converter;

import com.elite.miko.quiz.domain.model.result.QuizManageResult;
import com.elite.miko.quiz.domain.model.result.QuizQuestionResult;
import com.elite.miko.quiz.presentation.model.response.QuizManageListResponse;
import com.elite.miko.quiz.presentation.model.response.QuizQuestionListResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter {

    /**
     * クイズの問題取得結果用レスポンスを作成する
     *
     * @param result : DBのクイズ取得結果
     * @return {@link QuizQuestionListResponse}
     */
    public QuizQuestionListResponse convert(@NonNull QuizQuestionResult result) {
        // TODO 変換処理の盛り込み
        return new QuizQuestionListResponse();
    }

    /**
     * クイズ管理用情報一覧を持つレスポンスを作成する
     *
     * @param result : DBのクイズ取得結果
     * @return {@link QuizManageListResponse}
     */
    public QuizManageListResponse convert(@NonNull QuizManageResult result) {
        // TODO 変換処理の盛り込み
        return new QuizManageListResponse();
    }
}
