package com.elite.miko.quiz.application.exception;

import java.util.List;
import lombok.Getter;

/**
 * バリデーションエラー管理用自作例外
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -473405024738469097L;

    @Getter
    private final List<String> detailMessages;

    public ValidationException(List<String> detailMessage) {
        this("Validation Error", detailMessage);
    }

    public ValidationException(String message, List<String> detailMessage) {
        super(message);
        this.detailMessages = detailMessage;
    }
}
