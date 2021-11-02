package com.elite.miko.quiz.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TransactionException extends RuntimeException {

    private static final long serialVersionUID = -4646538132779601940L;

    public TransactionException(String message) {
        super(message);
    }
}
