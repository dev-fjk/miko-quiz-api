package com.elite.miko.quiz.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Controllerの事前の共通認証処理を行うクラス
 * TODO 管理者用API向けのリクエストに対して 共通の認証処理を盛り込む
 */
@Slf4j
@Component
public class QuizInterceptor implements HandlerInterceptor {

    /**
     * Controllerの事前処理を行う
     *
     * @param request  : リクエスト
     * @param response : レスポンス
     * @param handler  : ハンドラ
     * @return 認証結果
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        log.info("管理API向けのリクエストを受信しました");
        return true;
    }
}
