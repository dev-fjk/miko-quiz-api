package com.elite.miko.quiz.presentation.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * クイズ問題一覧を返却するレスポンス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "クイズ管理用情報取得結果")
public class QuizManageListResponse {

    @Schema(description = "総件数", example = "10")
    private long total;

    @Schema(description = "取得開始位置", minimum = "1", example = "10")
    private long start;

    @Schema(description = "取得件数", minimum = "10", maximum = "100", example = "10")
    private int count;

    @Schema(description = "クイズ問題一覧")
    private List<QuizAll> quizList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "QuizQuestionResponse.QuizQuestion", description = "クイズ一覧取得結果")
    public static class QuizAll {

        @Schema(description = "クイズID", example = "1")
        private Long quizId;

        @Schema(description = "問題文", example = "さくらみこの挨拶といえば？")
        private String question;

        @Schema(description = "解説文", example = "さくらみこの挨拶はにゃっはろー")
        private String commentary;

        @Schema(description = "クイズの状態", example = "enabled")
        private String quizStatus;

        @Schema(description = "クイズの管理用回答情報")
        private QuizManageAnswer quizManageAnswer;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Schema(name = "QuizQuestionResponse.QuizQuestion,QuizAnswer", description = "クイズの管理用回答情報")
        public static class QuizManageAnswer {

            @Schema(description = "回答ID", example = "1")
            private Long answerId;

            @Schema(description = "回答1", example = "にゃっはろー")
            private String answer1;

            @Schema(description = "回答2", example = "こんこんきーつね")
            private String answer2;

            @Schema(description = "回答3", example = "こんやっぴー")
            private String answer3;

            @Schema(description = "回答4", example = "ゆびゆびー")
            private String answer4;

            @Schema(description = "正答番号 1から4のいずれかの数字を設定", example = "1")
            private int correctNumber;
        }
    }
}
