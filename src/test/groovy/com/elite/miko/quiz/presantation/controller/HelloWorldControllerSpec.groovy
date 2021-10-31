package com.elite.miko.quiz.presantation.controller

import spock.lang.Specification
import spock.lang.Unroll

class HelloWorldControllerSpec extends Specification {

    HelloWorldController target

    void setup() {
        target = new HelloWorldController()
    }

    @Unroll
    def "Get"() {
        when:
        target.get()
        then:
        noExceptionThrown()
    }
}
