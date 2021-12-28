package com.elite.miko.quiz.presentation.model.form;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description = "ログインリクエスト")
public class LoginForm {

    @NotBlank(message = "必須パラメータです")
    @Size(max = 12)
    @Schema(description = "アカウントID", required = true)
    private String accountId;

    @NotBlank(message = "必須パラメータです")
    @Schema(description = "パスワード", required = true)
    private String password;
}
