package com.elite.miko.quiz.presentation.converter;

import com.elite.miko.quiz.presentation.model.response.ProblemResponse;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

/**
 * エラーレスポンスの変換を行う
 */
@Component
public class ProblemConverter {

    /**
     * リクエスト時のValidationエラーを返却する
     *
     * @param exception {@link BindException}
     * @return {@link ProblemResponse}
     */
    public ProblemResponse convert(BindException exception) {
        return ProblemResponse.builder()
                .title("リクエストされたパラメータは正しくありません")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(detail(exception.getFieldErrors()))
                .build();
    }

    /**
     * リクエスト時のValidationエラーを返却する
     *
     * @param exception {@link ConstraintViolationException}
     * @return {@link ProblemResponse}
     */
    public ProblemResponse convert(ConstraintViolationException exception) {
        return ProblemResponse.builder()
                .title("リクエストされたパラメータは正しくありません")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(detail(exception.getConstraintViolations()))
                .build();
    }

    /**
     * 403エラーを返す
     *
     * @param exception {@link AccessDeniedException}
     * @return {@link ProblemResponse}
     */
    public ProblemResponse convert(AccessDeniedException exception) {
        return ProblemResponse.builder()
                .title(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.value())
                .detail("アクセスが拒否されました")
                .build();
    }

    /**
     * catchされなかったエラーを返す
     *
     * @param exception {@link RuntimeException}
     * @return {@link ProblemResponse}
     */
    public ProblemResponse convert(RuntimeException exception) {
        return ProblemResponse.builder()
                .title("予期しないエラーが発生しました")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .detail(exception.getMessage())
                .build();
    }

    /**
     * BindingResult使用時のバリデーションエラーの詳細を作成して変九役する
     *
     * @param errors : FieldErrors
     * @return エラー詳細
     */
    private String detail(List<FieldError> errors) {
        return errors.stream()
                .map(error -> error.getField() + " は " + error.getDefaultMessage() + ": " + error.getRejectedValue())
                .collect(Collectors.joining(", "));
    }

    /**
     * Validator使用時のバリデーションエラーの詳細を作成して変九役する
     *
     * @param violations : ConstraintViolationのセット
     * @return エラー詳細
     */
    private String detail(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(v -> v.getPropertyPath() + " は " + v.getMessage() + ": " + v.getInvalidValue())
                .collect(Collectors.joining(", "));
    }
}
