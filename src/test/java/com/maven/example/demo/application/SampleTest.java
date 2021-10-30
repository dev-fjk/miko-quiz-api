package com.maven.example.demo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Junit5 使用の場合のサンプル
 */
@SpringBootTest
class SampleTest {

    @Autowired
    private Sample target;

    @Test
    void sumTest() {
        int result = target.sum(1, 2);
        assertEquals(result, 3);
    }
}
