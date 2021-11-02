package com.elite.miko.quiz.domain.repository;

import com.elite.miko.quiz.domain.model.entity.Answer;
import org.springframework.lang.NonNull;

public interface AnswerRepository {

    int addAnswer(@NonNull Answer answer);
}
