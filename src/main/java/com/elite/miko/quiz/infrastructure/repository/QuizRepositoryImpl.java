package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.model.dto.QuizStatusType;
import com.elite.miko.quiz.domain.model.entity.Quiz;
import com.elite.miko.quiz.domain.model.entity.QuizJoinAnswer;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.infrastructure.dao.QuizDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuizRepositoryImpl implements QuizRepository {

    private final QuizDao quizDao;

    @Override
    public List<Integer> fetchAllQuizIdByStatus(QuizStatusType statusType) {
        log.info("status Type : {}", statusType.getValue());
        return quizDao.fetchAllQuizIdByStatus(statusType.getValue());
    }

    @Override
    public List<QuizJoinAnswer> fetchQuizData(List<Integer> idList, int count) {
        log.info("クイズ一覧を取得します。");
        log.info("idList : {},count : {}", idList, count);
        return quizDao.fetchQuizJoinAnswers(idList, count);
    }

    @Override
    public int createQuizRequest(@NonNull Quiz quiz) {
        return quizDao.createQuizRequest(quiz);
    }
}



