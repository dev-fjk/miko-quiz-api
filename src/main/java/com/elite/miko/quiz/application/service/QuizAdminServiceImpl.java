package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.application.exception.RepositoryControlException;
import com.elite.miko.quiz.application.exception.ResourceNotFoundException;
import com.elite.miko.quiz.domain.model.consts.QuizStatus;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.model.result.FetchQuizResult;
import com.elite.miko.quiz.domain.model.result.QuizManageResult;
import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.domain.service.QuizAdminService;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizAdminServiceImpl implements QuizAdminService {

    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    /**
     * クイズ一覧の取得を行う
     *
     * @param start : offset 取得開始
     * @param count : limit 取得件数
     * @return 取得結果
     */
    @Override
    public QuizManageResult fetchQuiz(int start, int count) {
        var fetchQuizResult = quizRepository.fetchQuiz(start, count);
        return this.commonManageQuizFetch(fetchQuizResult, start);
    }

    /**
     * リクエスト中のクイズ一覧の取得を行う
     *
     * @param start : offset 取得開始
     * @param count : limit 取得件数
     * @return 取得結果
     */
    @Override
    public QuizManageResult fetchRequestQuiz(int start, int count) {
        var fetchQuizResult = quizRepository.fetchQuiz(start, count, QuizStatus.REQUEST);
        return this.commonManageQuizFetch(fetchQuizResult, start);
    }

    /**
     * クイズの追加を行う
     *
     * @param quizAddDto : クイズ追加Dao
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void insertQuiz(QuizAddDto quizAddDto) {

        // 管理者側から追加する際は有効で登録
        quizAddDto.setStatus(QuizStatus.ENABLED);

        final Quiz insertedQuiz = quizRepository.insertQuiz(quizAddDto);
        boolean isInsertedAnswer = answerRepository.insertAnswer(insertedQuiz.getQuizId(), quizAddDto.getAnswer());
        if (!isInsertedAnswer) {
            throw new RepositoryControlException("回答の登録に失敗しました");
        }
    }

    @Override
    public void updateQuiz(QuizUpdateDto quizUpdateDto) {

    }

    @Override
    public void deleteQuiz(List<Long> quizIdList) {

    }

    /**
     * クイズ一覧を取得する際の共通処理を行う
     *
     * @param fetchQuizResult : クイズテーブルの取得結果
     * @param start           : 取得開始位置
     * @return {@link QuizManageResult}
     */
    private QuizManageResult commonManageQuizFetch(FetchQuizResult fetchQuizResult, int start) {

        if (fetchQuizResult.getTotal() == 0) {
            throw new ResourceNotFoundException("クイズが見つかりませんでした");
        }
        if (CollectionUtils.isEmpty(fetchQuizResult.getQuizList())) {
            // 取得結果が0件の場合は空のレスポンスを返却する
            return QuizManageResult.builder()
                    .total(fetchQuizResult.getTotal())
                    .start(start)
                    .quizList(Collections.emptyList())
                    .answerList(Collections.emptyList())
                    .build();
        }

        // 取得したクイズのIDを条件に回答一覧を取得する
        var quizList = fetchQuizResult.getQuizList();
        final List<Long> quizIdList = quizList.stream()
                .map(Quiz::getQuizId)
                .collect(Collectors.toList());

        return QuizManageResult.builder()
                .total(fetchQuizResult.getTotal())
                .start(start)
                .quizList(quizList)
                .answerList(answerRepository.fetchByQuizIdList(quizIdList))
                .build();
    }
}
