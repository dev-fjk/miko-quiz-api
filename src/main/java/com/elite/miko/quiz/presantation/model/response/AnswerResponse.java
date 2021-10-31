package com.elite.miko.quiz.presantation.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    private Integer id;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private int correctNumber;
}
