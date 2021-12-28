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
@Schema(description = "クイズ追加リクエスト")
public class QuizAddRequestForm extends QuizBaseRequest {

    private AddAnswer answer;

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = false)
    @Schema(description = "快投追加リクエスト")
    public static class AddAnswer extends AnswerBaseRequest {
    }
}
