package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.application.exception.RepositoryControlException;
import com.elite.miko.quiz.domain.model.consts.QuizStatus;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateDto;
import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.domain.service.QuizAdminService;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizAdminServiceImpl implements QuizAdminService {

    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

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
}
