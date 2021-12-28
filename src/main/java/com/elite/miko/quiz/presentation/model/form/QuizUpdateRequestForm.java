package com.elite.miko.quiz.presentation.model.form;

import com.elite.miko.quiz.presentation.model.form.base.AnswerBaseRequest;
import com.elite.miko.quiz.presentation.model.form.base.QuizBaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description = "クイズ更新リクエスト")
public class QuizUpdateRequestForm extends QuizBaseRequest {

    private Long quizId;

    private UpdateAnswer answer;

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = false)
    @Schema(description = "回答更新リクエスト")
    public static class UpdateAnswer extends AnswerBaseRequest {
    }
}
