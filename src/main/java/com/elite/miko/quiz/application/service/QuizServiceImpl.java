package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.domain.model.dto.QuizStatusType;
import com.elite.miko.quiz.domain.model.dto.ResultFetchQuizInfo;
import com.elite.miko.quiz.domain.model.entity.Quiz;
import com.elite.miko.quiz.domain.model.entity.QuizJoinAnswer;
import com.elite.miko.quiz.domain.repository.QuizRepository;
import com.elite.miko.quiz.domain.service.QuizService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;

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
}
