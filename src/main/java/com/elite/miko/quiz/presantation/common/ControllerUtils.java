package com.elite.miko.quiz.presantation.common;

import com.elite.miko.quiz.presantation.model.response.ErrorDetail;
import com.elite.miko.quiz.presantation.model.response.ErrorSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * Controllerの共通関数定義クラス
 */
@Slf4j
@Configuration
public class ControllerUtils {

    /**
     * Json形式のHTTPヘッダーを作成する
     *
     * @return jsonヘッダー
     */
    public HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    /**
     * バリデーションエラー時のレスポンスを作成する
     *
     * @param violations : バリデーションエラー
     * @return エラーレスポンス
     */
    public <T> ErrorSet createBadRequestErrorSet(final Set<ConstraintViolation<T>> violations) {
        final ErrorSet error = new ErrorSet();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Validation Error");

        final List<ErrorDetail> detailList = new ArrayList<>();
        for (String detailMessage : this.toDetailMessage(violations)) {
            ErrorDetail detail = new ErrorDetail();
            detail.setDetail(detailMessage);
            detail.setErrorCode(HttpStatus.BAD_REQUEST.toString());
            detailList.add(detail);
        }

        error.setDetail(detailList);
        return error;
    }

    /**
     * エラー詳細メッセージの加工を行う
     *
     * @param violations : バリデーションエラー
     * @return エラー詳細メッセージのリスト
     */
    public <T> List<String> toDetailMessage(final Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .map(violation -> violation.getPropertyPath() + "は" + violation.getMessage())
                .collect(Collectors.toList());
    }
}
