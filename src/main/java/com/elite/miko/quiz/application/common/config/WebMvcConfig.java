package com.elite.miko.quiz.application.common.config;

import com.elite.miko.quiz.presentation.controller.QuizAuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final QuizAuthorizationInterceptor quizAuthorizationInterceptor;

    /**
     * Interceptorの追加を行う
     *
     * @param registry : レジストリ
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(quizAuthorizationInterceptor);
    }
}
