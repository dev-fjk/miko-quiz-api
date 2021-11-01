package com.elite.miko.quiz.domain.model.dto;

public enum QuizStatusType {
    APPLY("apply"),
    REQUEST("request"),
    EXCLUSION("exclusion");

    private final String value;

    QuizStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
