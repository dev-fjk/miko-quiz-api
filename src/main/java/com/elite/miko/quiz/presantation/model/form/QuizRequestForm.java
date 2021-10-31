package com.elite.miko.quiz.presantation.model.form;

import io.swagger.annotations.ApiModelProperty;
import java.util.stream.IntStream;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequestForm {

    @NotBlank(message = "必須項目です")
    @Size(max = 200)
    @ApiModelProperty(value = "問題文", required = true)
    private String question;

    @NotBlank(message = "必須項目です")
    @Size(max = 200)
    @ApiModelProperty(value = "解説文", required = true)
    private String commentary;

    @NotBlank(message = "必須項目です")
    @Size(max = 50)
    @ApiModelProperty(value = "回答1", required = true)
    private String answer1;

    @NotBlank(message = "必須項目です")
    @Size(max = 50)
    @ApiModelProperty(value = "回答2", required = true)
    private String answer2;

    @NotBlank(message = "必須項目です")
    @Size(max = 50)
    @ApiModelProperty(value = "回答3", required = true)
    private String answer3;

    @NotBlank(message = "必須項目です")
    @Size(max = 50)
    @ApiModelProperty(value = "回答4", required = true)
    private String answer4;

    @NotNull(message = "必須項目です")
    @ApiModelProperty(value = "正解番号", required = true)
    private Integer correctNumber;

    @AssertTrue(message = "1,2,3,4 のいずれかを指定してください")
    public boolean isCorrectNumber() {
        return IntStream.of(1, 2, 3, 4).anyMatch(this.correctNumber::equals);
    }
}
