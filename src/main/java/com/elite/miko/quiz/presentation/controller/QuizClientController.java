package com.elite.miko.quiz.presentation.controller;

import com.elite.miko.quiz.common.constant.OpenApiConstant;
import com.elite.miko.quiz.presentation.model.form.QuizAddRequestForm;
import com.elite.miko.quiz.presentation.model.response.QuizQuestionListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = QuizClientController.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = QuizClientController.BASE_PATH, description = "一般利用者側向けのクイズAPI")
public class QuizClientController {

    public static final String BASE_PATH = "/miko/v1/client/";

    @GetMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "クイズの一覧を取得する")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "クイズ一覧", content = @Content(
                    schema = @Schema(implementation = QuizQuestionListResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> fetchQuiz(
            @RequestParam(name = "count", required = false, defaultValue = "10") @Size(min = 10, max = 100) int count) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "クイズの追加リクエストを行う")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = QuizAddRequestForm.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", ref = OpenApiConstant.INSERTED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> quizRequest(@Validated @RequestBody QuizAddRequestForm quizAddRequestForm) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
