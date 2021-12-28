package com.elite.miko.quiz.presentation.controller;

import com.elite.miko.quiz.application.exception.LoginFailureException;
import com.elite.miko.quiz.application.exception.RepositoryControlException;
import com.elite.miko.quiz.application.exception.ResourceNotFoundException;
import com.elite.miko.quiz.presentation.converter.ProblemConverter;
import com.elite.miko.quiz.presentation.model.response.ProblemResponse;
import java.nio.file.AccessDeniedException;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception発生時にエラーレスポンスに変換するHandler
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class QuizExceptionHandler {

    private final ProblemConverter problemConverter;

    /**
     * リクエストオブジェクトのバリデーションエラー
     *
     * @param exception {@link BindException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemResponse> handleBindException(BindException exception) {
        return this.errorResponses(HttpStatus.BAD_REQUEST, problemConverter.convert(exception));
    }

    /**
     * リクエストオブジェクトのバリデーションエラー
     *
     * @param exception {@link ConstraintViolationException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        return this.errorResponses(HttpStatus.BAD_REQUEST, problemConverter.convert(exception));
    }

    /**
     * ログイン失敗時のエラー
     *
     * @param exception {@link LoginFailureException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(LoginFailureException.class)
    public ResponseEntity<ProblemResponse> handleLoginFailureException(LoginFailureException exception) {
        return this.errorResponses(HttpStatus.UNAUTHORIZED, problemConverter.convert(exception));
    }

    /**
     * 権限がない時のエラー
     *
     * @param exception {@link AccessDeniedException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemResponse> handleAccessDeniedException(AccessDeniedException exception) {
        return this.errorResponses(HttpStatus.FORBIDDEN, problemConverter.convert(exception));
    }

    /**
     * リソースが見つからない場合のエラー
     *
     * @param exception {@link ResourceNotFoundException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return this.errorResponses(HttpStatus.NOT_FOUND, problemConverter.convert(exception));
    }

    /**
     * インフラ層でのデータ更新時の例外
     *
     * @param exception {@link RepositoryControlException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(RepositoryControlException.class)
    public ResponseEntity<ProblemResponse> handleRuntimeException(RepositoryControlException exception) {
        return this.errorResponses(HttpStatus.INTERNAL_SERVER_ERROR, problemConverter.convert(exception));
    }

    /**
     * その他エラー
     *
     * @param exception {@link RuntimeException}
     * @return エラーレスポンス
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemResponse> handleRuntimeException(RuntimeException exception) {
        return this.errorResponses(HttpStatus.INTERNAL_SERVER_ERROR, problemConverter.convert(exception));
    }

    /**
     * エラーレスポンスを作成する
     *
     * @param status : HTTPステータス
     * @param body   : HTTPボディ
     * @return エラーレスポンス
     */
    private ResponseEntity<ProblemResponse> errorResponses(HttpStatus status, ProblemResponse body) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}
