package com.elite.miko.quiz.presentation.controller;

import com.elite.miko.quiz.application.common.constant.OpenApiConstant;
import com.elite.miko.quiz.domain.model.dto.QuizAddDto;
import com.elite.miko.quiz.domain.model.dto.QuizUpdateListDto;
import com.elite.miko.quiz.domain.service.QuizAdminService;
import com.elite.miko.quiz.presentation.converter.ResponseConverter;
import com.elite.miko.quiz.presentation.model.form.QuizAddForm;
import com.elite.miko.quiz.presentation.model.form.QuizUpdateForm;
import com.elite.miko.quiz.presentation.model.response.QuizManageListResponse;
import com.elite.miko.quiz.presentation.validator.QuizValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@Tag(name = QuizAdminController.BASE_PATH, description = "?????????????????????API JWT?????????????????????")
public class QuizAdminController {

    public static final String BASE_PATH = "/miko/v1/admin/";

    private final QuizAdminService adminService;

    private final ResponseConverter responseConverter;
    private final QuizValidator quizValidator;
    private final ModelMapper modelMapper;

    /**
     * ?????????????????????????????????
     *
     * @param start : ??????????????????
     * @param count : ????????????
     * @return ????????????
     */
    @GetMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "?????????????????????????????????(?????????)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "???????????????(?????????)", content = @Content(
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
            @RequestParam(name = "count", required = false, defaultValue = "20") @Range(min = 1, max = 50) int count) {
        var quizManageResult = adminService.fetchQuiz(start - 1, count);
        return new ResponseEntity<>(responseConverter.convert(quizManageResult), HttpStatus.OK);
    }

    /**
     * ??????????????????????????????????????????????????????
     *
     * @param start : ??????????????????
     * @param count : ????????????
     * @return ????????????
     */
    @GetMapping(path = "/quizzes/requests")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "???????????????????????????????????????????????????")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "????????????????????????????????????", content = @Content(
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
            @RequestParam(name = "count", required = false, defaultValue = "20") @Range(min = 1, max = 50) int count) {
        var quizManageResult = adminService.fetchRequestQuiz(start - 1, count);
        return new ResponseEntity<>(responseConverter.convert(quizManageResult), HttpStatus.OK);
    }

    /**
     * ???????????????????????????
     *
     * @param quizAddForm : ???????????????Form
     * @return ??????????????????201?????????
     */
    @PostMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "???????????????????????????")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = QuizAddForm.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", ref = OpenApiConstant.INSERTED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> addQuiz(@Validated @RequestBody QuizAddForm quizAddForm) {
        adminService.insertQuiz(modelMapper.map(quizAddForm, QuizAddDto.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * ???????????????????????????
     *
     * @param quizUpdateForm : ??????????????????Form
     * @return ?????????????????????204?????????
     */
    @PutMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "???????????????????????????")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = QuizUpdateForm.class))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", ref = OpenApiConstant.UPDATED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> updateQuiz(@RequestBody QuizUpdateForm quizUpdateForm) {
        quizValidator.validate(quizUpdateForm);
        adminService.updateQuiz(modelMapper.map(quizUpdateForm, QuizUpdateListDto.class));
        return ResponseEntity.noContent().build();
    }

    /**
     * ????????????????????????ID???????????????????????????????????????
     *
     * @param quizIdList : ????????????????????????ID????????????????????????????????????????????????
     * @return ??????????????????204?????????
     */
    @DeleteMapping(path = "/quizzes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "???????????????????????????")
    @Parameters({
            @Parameter(name = "quizIdList", in = ParameterIn.QUERY,
                    description = "????????????????????????ID?????????????????????????????????????????? ??????50???",
                    schema = @Schema(type = "array", format = "int64")
            )
    })
    @ApiResponses({
            @ApiResponse(responseCode = "204", ref = OpenApiConstant.DELETED_SUCCESS),
            @ApiResponse(responseCode = "400", ref = OpenApiConstant.BAD_REQUEST),
            @ApiResponse(responseCode = "401", ref = OpenApiConstant.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", ref = OpenApiConstant.FORBIDDEN),
            @ApiResponse(responseCode = "404", ref = OpenApiConstant.QUIZ_NOT_FOUND),
            @ApiResponse(responseCode = "500", ref = OpenApiConstant.INTERNAL_SERVER_ERROR),
    })
    public ResponseEntity<?> deleteQuiz(@RequestParam(name = "quizIdList") @NotNull List<Long> quizIdList) {
        quizValidator.validate(quizIdList);
        adminService.deleteQuiz(quizIdList);
        return ResponseEntity.noContent().build();
    }
}
