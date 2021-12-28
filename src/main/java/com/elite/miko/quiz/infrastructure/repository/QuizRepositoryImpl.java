package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.infrastructure.dao.QuizDao;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuizRepositoryImpl implements QuizRepository {

    private final QuizDao quizDao;

    /**
     * クイズの問題をランダムで指定件数文取得する
     *
     * @param count : 指定取得件数
     * @return クイズ一覧
     */
    @Override
    public List<Quiz> fetchRandomQuiz(int count) {
        final SelectOptions options = SelectOptions.get().limit(count);
        return quizDao.fetchQuizRandom(options);
    }

    @Override
    public Object fetchQuiz(int start, int count) {
        return null;
    }

    @Override
    public Object fetchRequestQuiz(int start, int count) {
        return null;
    }

    @Override
    public void insertQuiz(QuizAddDto quizAddDto) {

    }

    @Override
    public void updateQuiz(QuizUpdateDto quizUpdateDto) {

    }

    @Override
    public void deleteQuiz(List<Long> quizIdList) {

    }
}
