package com.elite.miko.quiz

import spock.lang.Specification
import spock.lang.Unroll

class ApplicationSpec extends Specification {

    @Unroll
    def "Main"() {
        when:
        Application.main(new String[]{"dummy"})
        then:
        noExceptionThrown()
    }
}
