package com.maven.example.demo.application

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock使用の場合のサンプル
 */
class SampleSpec extends Specification {

    Sample sut

    @Unroll
    def "足し算テスト"() {
        given:
        sut = new Sample()
        def input1 = a
        def input2 = b

        when:
        def result = sut.sum(input1, input2)

        then:
        result == expected

        where:
        a | b | expected
        1 | 1 | 2
        2 | 2 | 4
        0 | 1 | 1
    }
}
