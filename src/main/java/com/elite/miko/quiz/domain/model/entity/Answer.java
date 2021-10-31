package com.elite.miko.quiz.domain.model.entity;

import java.util.Date;
import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

@Data
@Table(name = "answer")
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private int correctNumber;

    private Date createdAt;

    private Date updateAt;
}
