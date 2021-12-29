package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.application.exception.RepositoryControlException;
import com.elite.miko.quiz.domain.model.consts.QuizStatus;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.model.result.FetchQuizResult;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.infrastructure.dao.QuizDao;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.seasar.doma.jdbc.SelectOptions;
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
     * @return クイズ取得結果
     */
    @Override
    public FetchQuizResult fetchRandomQuiz(int count) {
        final var quizList = quizDao.fetchQuizRandom(count, QuizStatus.ENABLED.getValue());
        return FetchQuizResult.builder()
                .quizList(quizList)
                .build();
    }

    /**
     * クイズ一覧の取得を行う
     *
     * @param start : offset 取得開始
     * @param count : limit 取得件数
     * @return クイズ取得結果
     */
    @Override
    public FetchQuizResult fetchQuiz(int start, int count) {
        return this.fetchQuiz(start, count, null);
    }

    /**
     * クイズ一覧の取得を行う
     *
     * @param start  : offset 取得開始
     * @param count  : limit 取得件数
     * @param status : 取得条件に指定するクイズの状態
     * @return クイズ取得結果
     */
    @Override
    public FetchQuizResult fetchQuiz(int start, int count, final QuizStatus status) {

        SelectOptions options = SelectOptions.get().offset(start).limit(count).count();
        final var quizList = Objects.nonNull(status)
                ? quizDao.fetchByIfPresentStatus(options, status.getValue())
                : quizDao.fetchByIfPresentStatus(options, null);

        return FetchQuizResult.builder()
                .total(options.getCount())
                .quizList(quizList)
                .build();
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
