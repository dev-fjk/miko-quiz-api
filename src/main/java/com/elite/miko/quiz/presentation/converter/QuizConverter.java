package com.elite.miko.quiz.presentation.converter;

import com.elite.miko.quiz.domain.model.result.QuizQuestionResult;
import com.elite.miko.quiz.infrastructure.model.entity.Answer;
import com.elite.miko.quiz.presentation.model.response.QuizQuestionListResponse.QuizQuestion;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class QuizConverter {

    /**
     * QuizQuestionResultからレスポンスへの変換を行う
     *
     * @param result : クイズ取得結果
     * @return クイズ一覧
     */
    public List<QuizQuestion> convert(QuizQuestionResult result) {

        // クイズIDと回答のMapを作成する
        final var quizIdPairAnswerMap = this.createQuizIdPairAnswerMap(result.getAnswerList());
        return result.getQuizList().stream().map(quiz -> {

            final Answer tmpAnswer = quizIdPairAnswerMap.get(quiz.getQuizId());
            return QuizQuestion.builder()
                    .quizId(quiz.getQuizId())
                    .question(quiz.getQuestion())
                    .commentary(quiz.getCommentary())
                    .quizAnswer(this.convertAnswerToQuizAnswer(tmpAnswer))
                    .build();

        }).collect(Collectors.toList());
    }

    /**
     * AnswerからQuizAnswerへ変換する
     *
     * @param answer : DBから取得した回答情報
     * @return レスポンス用の回答情報
     */
    private QuizQuestion.QuizAnswer convertAnswerToQuizAnswer(final Answer answer) {
        return QuizQuestion.QuizAnswer.builder()
                .answerId(answer.getAnswerId())
                .answer1(answer.getAnswer1())
                .answer2(answer.getAnswer2())
                .answer3(answer.getAnswer3())
                .answer4(answer.getAnswer4())
                .correctNumber(answer.getCorrectNumber())
                .build();
    }

    /**
     * QuizIDと回答のマップを作成して返却する
     *
     * @param answerList : 回答一覧
     * @return キー クイズID 値 AnswerのMap
     */
    private Map<Long, Answer> createQuizIdPairAnswerMap(List<Answer> answerList) {
        return answerList.stream().collect(
                Collectors.toMap(Answer::getQuizId, UnaryOperator.identity())
        );
    }
}
