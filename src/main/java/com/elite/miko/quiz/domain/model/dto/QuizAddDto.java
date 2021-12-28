package com.elite.miko.quiz.domain.model.dto;

import com.elite.miko.quiz.presentation.model.form.base.AnswerBaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class QuizAddDto {

    private String question;

    private String commentary;

    private Long answerId;

    private Long statusId;

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = false)
    @Schema(description = "快投追加リクエスト")
    public static class AddAnswer {
    }
}
