package com.elite.miko.quiz.domain.model.dto;

import lombok.Data;

@Data
public class QuizAddDto {

    private String question;

    private String commentary;

    private Long answerId;

    private Long statusId;
}
