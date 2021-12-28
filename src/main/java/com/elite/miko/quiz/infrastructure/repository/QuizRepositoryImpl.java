package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.application.exception.RepositoryControlException;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.infrastructure.dao.QuizDao;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuizRepositoryImpl implements QuizRepository {

    private final QuizDao quizDao;
    private final ModelMapper modelMapper;

    /**
     * クイズの問題をランダムで指定件数文取得する
     *
     * @param count : 指定取得件数
     * @return クイズ一覧
     */
    @Override
    public List<Quiz> fetchRandomQuiz(int count) {
        return quizDao.fetchQuizRandom(count);
    }

    @Override
    public Object fetchQuiz(int start, int count) {
        return null;
    }

    @Override
    public Object fetchRequestQuiz(int start, int count) {
        return null;
    }

    /**
     * クイズの追加を行う
     * IDが自動採番されたQuizのインスタンスを返却する
     *
     * @param quizAddDto : クイズ追加Dto
     * @return IDが自動採番されたQuizのインスタンス
     */
    @Override
    public Quiz insertQuiz(QuizAddDto quizAddDto) {

        // insert後にDB側で採番されたIDがQuizクラスのquizIdに設定されている
        final Quiz insertQuiz = modelMapper.map(quizAddDto, Quiz.class);
        int insertCount = quizDao.insert(insertQuiz);
        if (insertCount < 1) {
            throw new RepositoryControlException("クイズの登録に失敗しました");
        }

        log.info("登録したクイズ情報 : {}", insertQuiz);
        return insertQuiz;
    }

    @Override
    public void updateQuiz(QuizUpdateDto quizUpdateDto) {

    }

    @Override
    public void deleteQuiz(List<Long> quizIdList) {

    }
}
