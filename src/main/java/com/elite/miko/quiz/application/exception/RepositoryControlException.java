package com.elite.miko.quiz.application.exception;

/**
 * リポジトリ操作時の例外
 */
public class RepositoryControlException extends RuntimeException {

    private static final long serialVersionUID = 1050198995395212276L;

    public RepositoryControlException(String message) {
        super(message);
    }
}
