package com.elite.miko.quiz.presentation.controller;

import com.elite.miko.quiz.application.common.config.TokenConfig;
import com.elite.miko.quiz.application.common.utility.WebTokenUtil;
import java.nio.file.AccessDeniedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Controllerの事前の共通認証処理を行うクラス
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class QuizAuthorizationInterceptor implements HandlerInterceptor {

    public static final String X_QUIZ_AUTHORIZATION_HEADER = "X-quiz-authorization-header";

    private final TokenConfig tokenConfig;
    private final WebTokenUtil webTokenUtil;

    /**
     * Controllerの事前処理を行う
     *
     * @param request  : リクエスト
     * @param response : レスポンス
     * @param handler  : ハンドラ
     * @return 認証結果
     *
     * @throws AccessDeniedException 認証失敗時にThrow
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws AccessDeniedException {

        // 管理者用ページ以外へのアクセスの場合は認証を無効にする
        if (!request.getRequestURI().contains("admin")) {
            return true;
        }
        // JWTのトークン値の検証
        if (!authorize(request)) {
            throw new AccessDeniedException("認証に失敗しました");
        }
        return true;
    }

    /**
     * ヘッダー部の認証トークンの検証を行う
     *
     * @param request : 受領したリクエスト
     * @return 認証結果
     */
    private boolean authorize(HttpServletRequest request) {

        final String authorization = request.getHeader(X_QUIZ_AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(authorization)) {
            return false;
        }
        //Bearer tokenの形式であることをチェック
        if (authorization.indexOf("Bearer ") != 0) {
            return false;
        }

        try {
            // Bearer の部分の文字列を除いたTokenの部分だけを切りだしトークンを解析する
            final String token = authorization.substring(7);
            final String subject = webTokenUtil.analysisFromToken(token);

            // JWT作成時に使用したsubjectの値と一致するかチェック
            // 1アカウントしか使用しない関係で環境別に用意した環境変数のsubjectと一致するかをチェックする
            return subject.equals(tokenConfig.getSubject());

        } catch (RuntimeException runtimeException) {
            // JWT側でのエラー発生時は認証失敗とするためfalseを返却
            log.error("token解析時に失敗しました : {}", runtimeException.getMessage());
            return false;
        }
    }
}
