package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.model.entity.Answer;
import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.infrastructure.dao.AnswerDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository {

    private final AnswerDao answerDao;

    @Override
    public int addAnswer(@NonNull Answer answer) {
        return (answerDao.createAnswer(answer) == 1) ? answer.getId() : 0;
    }
}
