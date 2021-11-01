package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.model.entity.Quiz;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.infrastructure.dao.QuizDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuizRepositoryImpl implements QuizRepository {

    private final QuizDao quizDao;

    @Override
    public List<Quiz> fetchAll() {
        log.info("fetchAll!");
        List<Quiz> allQuiz = quizDao.fetchAll();
        log.info("allQuiz : {}", allQuiz);
        return allQuiz;
    }

    @Override
    public List<Integer> fetchAllApplyQuizId() {
        return quizDao.fetchAllApplyQuizId();
    }
}



