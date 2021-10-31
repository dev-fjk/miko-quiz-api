package com.elite.miko.quiz.presantation.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ErrorSet {

    @JsonProperty("Code")
    private Integer code;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Detail")
    private List<ErrorDetail> detail;
}
