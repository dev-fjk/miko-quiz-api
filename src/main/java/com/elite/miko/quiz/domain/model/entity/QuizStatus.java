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
@Table(name = "quiz_status")
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class QuizStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    private Date createdAt;

    private Date updateAt;
}
