package com.maven.example.demo.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Sample {

    /**
     * 足し算を行う
     *
     * @param a : 第一引数
     * @param b : 第二引数
     * @return 加算した値
     */
    public int sum(int a, int b) {
        log.info("sum START");

        int result = a + b;

        log.info("result : {}", result);
        log.info("sum END");
        return result;
    }
}
