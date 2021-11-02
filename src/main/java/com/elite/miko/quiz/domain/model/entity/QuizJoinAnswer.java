package com.elite.miko.quiz.domain.model.entity;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

@Data
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "quiz")
public class QuizJoinAnswer {

    @Id
    private Integer id;
    
    @Id
    private Integer answerId;

    private String question;

    private String commentary;

    private Integer statusId;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private int correctNumber;
}
