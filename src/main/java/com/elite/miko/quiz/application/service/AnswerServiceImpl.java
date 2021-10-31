package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.domain.model.Answer;
import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.domain.service.AnswerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public List<Answer> fetchAll() {
        return answerRepository.fetchAll();
    }
}
