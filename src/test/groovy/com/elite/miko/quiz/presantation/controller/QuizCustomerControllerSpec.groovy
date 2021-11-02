package com.elite.miko.quiz.presantation.controller

import com.elite.miko.quiz.domain.service.QuizService
import com.elite.miko.quiz.presantation.common.ControllerUtils
import org.spockframework.spring.SpringBean
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validator

class QuizCustomerControllerSpec extends Specification {

    QuizCustomerController target

    @SpringBean
    ControllerUtils commonBase = Mock();
    @SpringBean
    Validator validator = Mock();
    @SpringBean
    QuizService quizService = Mock();

    @Unroll
    def "sample"() {
        given:
        target = new QuizCustomerController(
                commonBase, validator, quizService
        )
        when:
        target.fetchQuizData(10);
        then:
        noExceptionThrown()
    }

}
