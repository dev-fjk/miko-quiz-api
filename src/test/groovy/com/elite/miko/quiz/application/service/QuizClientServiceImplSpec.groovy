package com.elite.miko.quiz.application.service

import com.elite.miko.quiz.application.exception.QuizNotEnoughCountException
import com.elite.miko.quiz.domain.model.result.FetchQuizResult
import com.elite.miko.quiz.domain.repository.AnswerRepository
import com.elite.miko.quiz.domain.repository.QuizRepository
import com.elite.miko.quiz.domain.service.QuizClientService
import com.elite.miko.quiz.infrastructure.model.entity.Answer
import com.elite.miko.quiz.infrastructure.model.entity.Quiz
import spock.lang.Specification

class QuizClientServiceImplSpec extends Specification {

    QuizClientService target

    def quizRepository = Mock(QuizRepository)
    def answerRepository = Mock(AnswerRepository)

    def setup() {
        target = new QuizClientServiceImpl(quizRepository, answerRepository)
    }

    def "正常系_fetchQuiz"() {
        given:
        def count = 10

        def quizList = (1..10).collect() {
            def tmpQuiz = new Quiz()
            tmpQuiz.setQuizId(it)
            return tmpQuiz
        }
        1 * quizRepository.fetchRandomQuiz(count) >> FetchQuizResult.builder()
                .total(10)
                .quizList(quizList)
                .build()
        1 * answerRepository.fetchByQuizIdList(_) >> (1..10).collect() {
            return new Answer()
        }

        when:
        def actual = target.fetchQuiz(count)

        then:
        actual.getQuizList().size() == 10
        actual.getAnswerList().size() == 10
    }

    def "異常系_fetchQuiz_クイズが指定件数以下の場合例外が発生"() {
        given:
        def count = 10

        def quizList = (1..9).collect() {
            def tmpQuiz = new Quiz()
            tmpQuiz.setQuizId(it)
            return tmpQuiz
        }
        1 * quizRepository.fetchRandomQuiz(count) >> FetchQuizResult.builder()
                .total(9)
                .quizList(quizList)
                .build()

        when:
        target.fetchQuiz(count)

        then:
        def exception = thrown(QuizNotEnoughCountException)
        exception.getMessage() == "指定された件数分のクイズが見つかりません"
        0 * answerRepository.fetchByQuizIdList(_)
    }
}
