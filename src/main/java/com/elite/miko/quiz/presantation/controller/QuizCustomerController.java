package com.elite.miko.quiz.presantation.controller;

import com.elite.miko.quiz.presantation.model.form.QuizRequestForm;
import com.elite.miko.quiz.presantation.model.response.ErrorSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一般利用向けのクイズコントローラー
 * クイズの取得と、クイズ追加リクエストが可能
 */
@Api(tags = "一般利用向けクイズIF")
@Slf4j
@RestController
@RequestMapping("/miko/v1/customer/")
@RequiredArgsConstructor
public class QuizCustomerController {

    private final ControllerBase commonBase;
    private final Validator validator;

    /**
     * クイズの追加リクエストを行う
     *
     * @param request : クイズ追加リクエスト
     * @return 更新件数を返却
     */
    @ApiOperation("クイズ追加リクエスト")
    @PostMapping(value = "/quiz/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "クイズリクエスト成功", response = Integer.class),
            @ApiResponse(code = 400, message = "バリデーションエラー", response = ErrorSet.class),
            @ApiResponse(code = 500, message = "システムエラー", response = ErrorSet.class)
    })
    public ResponseEntity<?> quizRequest(@RequestBody QuizRequestForm request) {

        Set<ConstraintViolation<QuizRequestForm>> violations = validator.validate(request);
        if (CollectionUtils.isNotEmpty(violations)) {
            ErrorSet errorSet = commonBase.createBadRequestErrorSet(violations);
            return new ResponseEntity<>(errorSet, commonBase.createHeader(), HttpStatus.BAD_REQUEST);
        }

        // TODO DBアクセスまでの一連処理の盛り込み
        return new ResponseEntity<>(1, commonBase.createHeader(), HttpStatus.CREATED);
    }
}
