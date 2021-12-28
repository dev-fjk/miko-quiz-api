package com.elite.miko.quiz.common.config;

import com.elite.miko.quiz.presentation.controller.QuizInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final QuizInterceptor quizInterceptor;

    /**
     * Interceptorの追加を行う
     *
     * @param registry : レジストリ
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(quizInterceptor).addPathPatterns("**/miko/quiz/admin/*");
    }
}
