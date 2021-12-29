package com.elite.miko.quiz.presentation.controller;

import com.elite.miko.quiz.application.common.constant.OpenApiConstant;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.service.QuizAdminService;
import com.elite.miko.quiz.presentation.model.form.QuizAddRequestForm;
import com.elite.miko.quiz.presentation.model.form.QuizUpdateRequestForm;
import com.elite.miko.quiz.presentation.model.response.QuizManageListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = QuizAdminController.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = QuizAdminController.BASE_PATH, description = "クイズ管理用のAPI")
public class QuizAdminController {

    public static final String BASE_PATH = "/miko/v1/admin/";

    private final QuizAdminService adminService;
    private final ModelMapper modelMapper;

    @GetMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "クイズの一覧を取得する(管理用)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "クイズ一覧(管理用)", content = @Content(
                    schema = @Schema(implementation = QuizManageListResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> fetchQuiz(
            @RequestParam(name = "start", required = false, defaultValue = "1") @Min(1) int start,
            @RequestParam(name = "count", required = false, defaultValue = "20") @Size(max = 50) int count) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/quizzes/requests")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "リクエスト中のクイズ一覧を取得する")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "リクエスト中のクイズ一覧", content = @Content(
                    schema = @Schema(implementation = QuizManageListResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.REQUEST_QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> fetchQuizRequest(
            @RequestParam(name = "start", required = false, defaultValue = "1") @Min(1) int start,
            @RequestParam(name = "count", required = false, defaultValue = "20") @Size(max = 50) int count) {
        return ResponseEntity.ok().build();
    }

    /**
     * クイズの追加を行う
     *
     * @param quizAddRequestForm : クイズ追加Form
     * @return 追加成功時は201を返却
     */
    @PostMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "クイズの追加を行う")
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
    public ResponseEntity<?> addQuiz(@Validated @RequestBody QuizAddRequestForm quizAddRequestForm) {
        adminService.insertQuiz(modelMapper.map(quizAddRequestForm, QuizAddDto.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/quizzes/{quizId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "クイズの更新を行う")
    @Parameters({
            @Parameter(name = "quizId", ref = OpenApiConstant.QUIZ_ID)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = QuizUpdateRequestForm.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", ref = OpenApiConstant.UPDATED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> updateQuiz(
            @PathVariable("quizId") @Min(1) long quizId,
            @Validated @RequestBody QuizUpdateRequestForm quizUpdateRequestForm) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/quizzes/{quizId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "クイズの削除を行う")
    @Parameters({
            @Parameter(name = "quizId", ref = OpenApiConstant.QUIZ_ID)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "201", ref = OpenApiConstant.INSERTED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> deleteQuiz(@PathVariable("quizId") @Min(1) long quizId) {
        return ResponseEntity.noContent().build();
    }
}
