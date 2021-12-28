package com.elite.miko.quiz.domain.model.result;

import com.elite.miko.quiz.infrastructure.model.entity.Answer;
import com.elite.miko.quiz.infrastructure.model.entity.Quiz;
import java.util.List;

/**
 * クイズの問題取得結果
 */
public class QuizQuestionResult {

    List<Quiz> quizList;

    List<Answer> answerList;

    /**
     * 取得したクイズ件数を取得する
     *
     * @return 取得件数
     */
    public int getCount() {
        return quizList.size();
    }
}
