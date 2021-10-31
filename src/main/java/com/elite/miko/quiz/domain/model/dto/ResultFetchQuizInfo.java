package com.elite.miko.quiz.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取得したデータをフロント側に必要な情報だけに加工する用のModel
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultFetchQuizInfo {

    private String question;
    private String commentary;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctNumber;
}
