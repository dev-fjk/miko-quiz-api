package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.model.dto.QuizAddDto.AnswerAddDto;
import com.elite.miko.quiz.domain.repository.AnswerRepository;
import com.elite.miko.quiz.infrastructure.dao.AnswerDao;
import com.elite.miko.quiz.infrastructure.model.entity.Answer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository {

    private final AnswerDao answerDao;
    private final ModelMapper modelMapper;

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

    /**
     * 回答の追加を行う
     *
     * @param quizId       : クイズID
     * @param answerAddDto : 回答追加Dto
     * @return 更新成功の場合trueを返却
     */
    @Override
    public boolean insertAnswer(long quizId, @NonNull AnswerAddDto answerAddDto) {
        final Answer insertAnswer = modelMapper.map(answerAddDto, Answer.class);
        insertAnswer.setQuizId(quizId);
        return answerDao.insert(insertAnswer) == 1;
    }

    /**
     * 回答の削除を行う
     *
     * @param quizIdList : 削除対象のクイズのクイズIDリスト
     */
    @Override
    public void deleteByQuizIdList(@NonNull List<Long> quizIdList) {
        answerDao.deleteByQuizIdList(quizIdList);
    }
}
