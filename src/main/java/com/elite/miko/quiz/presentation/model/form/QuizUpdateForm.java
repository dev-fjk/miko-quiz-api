package com.elite.miko.quiz.presentation.model.form;

import com.elite.miko.quiz.presentation.model.form.base.AnswerBaseRequest;
import com.elite.miko.quiz.presentation.model.form.base.QuizBaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description = "クイズ更新リクエスト")
public class QuizUpdateForm extends QuizBaseRequest {

    @NotNull(message = "必須パラメータです")
    @Schema(description = "クイズID", example = "1", required = true)
    private Long quizId;

    @Valid
    @Schema(description = "クイズの回答", required = true)
    private UpdateAnswer answer;

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = false)
    @Schema(description = "回答更新リクエスト")
    public static class UpdateAnswer extends AnswerBaseRequest {

        @NotNull(message = "必須パラメータです")
        @Schema(description = "回答ID", example = "10", required = true)
        private Long answerId;
    }
}
