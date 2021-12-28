package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.infrastructure.dao.AnswerDao;
import com.elite.miko.quiz.infrastructure.model.entity.Answer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository {

    private final AnswerDao answerDao;

    /**
     * クイズIDの一覧と一致する回答一覧を取得する
     *
     * @param quizIdList : クイズIDのリスト
     * @return 回答一覧
     */
    @Override
    public List<Answer> fetchByQuizIdList(@NonNull List<Long> quizIdList) {
        return answerDao.fetchByQuizIdList(quizIdList);
    }
}
