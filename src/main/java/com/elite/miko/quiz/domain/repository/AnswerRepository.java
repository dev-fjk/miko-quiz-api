package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.entity.Answer;
import java.util.List;

public interface AnswerRepository {

    List<Answer> fetchAll();

}
