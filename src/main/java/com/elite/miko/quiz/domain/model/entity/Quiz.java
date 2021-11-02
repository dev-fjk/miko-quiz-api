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
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "quiz")
public class Quiz {

    @Id
    private Integer id;

    @Id
    private Integer answerId;

    private String question;

    private String commentary;

    private Integer statusId;

    private Date createdAt;

    private Date updateAt;
}
