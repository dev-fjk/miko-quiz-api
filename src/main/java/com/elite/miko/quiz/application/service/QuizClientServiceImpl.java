package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.application.exception.ResourceNotFoundException;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.result.QuizQuestionResult;
import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.domain.service.QuizClientService;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizClientServiceImpl implements QuizClientService {

    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    /**
     * クライアント向けのクイズ問題の取得を行う
     *
     * @param count : 取得指定件数
     * @return クイズ取得結果
     */
    @Override
    public QuizQuestionResult fetchQuiz(int count) {

        final List<Quiz> quizList = quizRepository.fetchRandomQuiz(count);
        if (CollectionUtils.isEmpty(quizList)) {
            throw new ResourceNotFoundException("クイズが見つかりませんでした");
        }

        final List<Long> quizIdList = quizList.stream()
                .map(Quiz::getQuizId)
                .collect(Collectors.toList());

        return QuizQuestionResult.builder()
                .quizList(quizList)
                .answerList(answerRepository.fetchByQuizIdList(quizIdList))
                .build();
    }

    @Override
    public void quizRequest(QuizAddDto quizAddDto) {

    }
}
