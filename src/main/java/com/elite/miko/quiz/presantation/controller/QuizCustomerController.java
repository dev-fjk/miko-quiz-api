package com.elite.miko.quiz.presantation.controller;

import com.elite.miko.quiz.presantation.common.ControllerUtils;
import com.elite.miko.quiz.presantation.model.form.QuizRequestForm;
import com.elite.miko.quiz.presantation.model.response.ErrorDetail;
import com.elite.miko.quiz.presantation.model.response.ErrorSet;
import com.elite.miko.quiz.presantation.model.response.QuizResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final ControllerUtils commonBase;
    private final Validator validator;

    /**
     * クイズの検索を行う
     *
     * @param count : クイズ取得件数 10~100までの任意の件数
     * @return 更新件数を返却
     */
    @ApiOperation("クイズ取得リクエスト")
    @GetMapping(value = "/quiz/{count}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "クイズ取得成功", response = QuizResponse.class),
            @ApiResponse(code = 204, message = "クイズデータ無し", response = ErrorSet.class),
            @ApiResponse(code = 400, message = "入力値エラー", response = ErrorSet.class),
            @ApiResponse(code = 500, message = "システムエラー", response = ErrorSet.class)
    })
    public ResponseEntity<?> fetchQuizData(@PathVariable Integer count) {

        if (!this.checkQuizCount(count)) {
            // バリデーションエラーの成形
            final List<ErrorDetail> detailList = new ArrayList<>();
            detailList.add(new ErrorDetail("countは10から100までの10件単位の件数を指定してください",
                    HttpStatus.BAD_REQUEST.toString()));

            final ErrorSet error = new ErrorSet(HttpStatus.BAD_REQUEST.value(),
                    "Validation Error", detailList);
            return new ResponseEntity<>(error, commonBase.createHeader(), HttpStatus.BAD_REQUEST);
        }

        // TODO DBアクセスまでの一連処理の盛り込み
        return new ResponseEntity<>(new QuizResponse(), commonBase.createHeader(), HttpStatus.CREATED);
    }

    /**
     * クイズの追加リクエストを行う
     *
     * @param request : クイズ追加リクエスト
     * @return 更新件数を返却
     */
    @ApiOperation("クイズ追加リクエスト")
    @PostMapping(value = "/quiz/")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "クイズリクエスト成功", response = Integer.class),
            @ApiResponse(code = 400, message = "入力値エラー", response = ErrorSet.class),
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

    /**
     * クイズ取得指定件数の バリデーションチェックを実施
     *
     * @param count : 取得指定件数
     * @return チェック結果
     */
    private boolean checkQuizCount(Integer count) {

        Integer[] checkNumbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        boolean result = true;
        if (Objects.isNull(count) || count.equals(0)) {
            log.error("count is Not setting : {}", count);
            result = false;
        }
        if (!Arrays.asList(checkNumbers).contains(count)) {
            log.error("count is not in units of 10");
            result = false;
        }
        return result;
    }
}
