package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.application.exception.TransactionException;
import com.elite.miko.quiz.domain.model.dto.QuizStatusType;
import com.elite.miko.quiz.domain.model.dto.ResultFetchQuizInfo;
import com.elite.miko.quiz.domain.model.entity.Answer;
import com.elite.miko.quiz.domain.model.entity.Quiz;
import com.elite.miko.quiz.domain.model.entity.QuizJoinAnswer;
import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.domain.service.QuizService;
import com.elite.miko.quiz.presantation.model.form.QuizRequestForm;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    @Override
    public List<ResultFetchQuizInfo> fetchQuiz(int count) {

        // クイズIDの一覧を取得
        List<Integer> applyQuizIds = quizRepository.fetchAllQuizIdByStatus(QuizStatusType.APPLY);
        log.info("クイズID一覧 : {}", applyQuizIds);
        if (CollectionUtils.isEmpty(applyQuizIds)) {
            log.error("クイズが見つかりません。");
            return new ArrayList<>();
        }

        // クイズ一覧を取得
        List<QuizJoinAnswer> quizList = quizRepository.fetchQuizData(applyQuizIds, count);
        if (quizList.size() < count) {
            log.warn("クイズの件数が指定件数に足りませんでした。");
        }

        List<ResultFetchQuizInfo> result = new ArrayList<>();
        quizList.forEach(tmp -> {
            result.add(modelMapper.map(tmp, ResultFetchQuizInfo.class));
        });
        return result;
    }

    /**
     * クイズリクエストを追加する
     *
     * @param quizRequestForm : クイズリクエスト
     * @return : 更新結果
     */
    @Transactional(rollbackFor = Throwable.class, timeout = 20)
    @Override
    public int addQuizRequest(QuizRequestForm quizRequestForm) {

        Answer answer = modelMapper.map(quizRequestForm, Answer.class);
        int answerId = answerRepository.addAnswer(answer);
        if (answerId == 0) {
            // answerIdが0の場合例外発行
            log.error("回答の登録に失敗しました。");
            throw new TransactionException("クイズのリクエストに失敗しました");
        }

        Quiz quiz = modelMapper.map(quizRequestForm, Quiz.class);
        quiz.setAnswerId(answerId);

        int createCount = quizRepository.createQuizRequest(quiz);
        if (createCount != 1) {
            // answerIdが0の場合例外発行
            log.error("問題の登録に失敗しました。");
            throw new TransactionException("クイズのリクエストに失敗しました");
        }
        return createCount;
    }
}
