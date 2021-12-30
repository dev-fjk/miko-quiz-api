package com.elite.miko.quiz.application.common.config;

import com.elite.miko.quiz.application.common.constant.OpenApiConstant;
import com.elite.miko.quiz.presentation.controller.QuizAuthorizationInterceptor;
import com.elite.miko.quiz.presentation.model.response.ProblemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * SpringDocで生成される RestApi仕様書に関する設定
 */
@Configuration
public class OpenApiConfig {

    /**
     * ジェネレートされたOpenApi定義の拡張設定
     *
     * @param objectMapper : objectMapper
     * @return OpenApiCustomiser
     */
    @Bean
    public OpenApiCustomiser openApiCustomiser(ObjectMapper objectMapper) {
        return openApi -> {
            addSchemas(openApi.getComponents());
            addParameters(openApi.getComponents());
            addResponses(openApi.getComponents(), objectMapper);
        };
    }

    /**
     * パラメータ定義を追加する
     *
     * @param components : コンポーネント
     */
    private void addParameters(Components components) {
        components.addParameters(OpenApiConstant.AUTHORIZATION_HEADER, new Parameter()
                .name(QuizAuthorizationInterceptor.X_QUIZ_AUTHORIZATION_HEADER)
                .description("認証ヘッダー")
                .in("header")
                .style(Parameter.StyleEnum.SIMPLE)
                .required(true)
        );
    }

    /**
     * Schema定義の追加
     *
     * @param components : コンポーネント
     */
    private void addSchemas(Components components) {
        var schemas = ModelConverters.getInstance().read(ProblemResponse.class);
        schemas.forEach(components::addSchemas);
    }

    /**
     * レスポンス定義を追加する
     *
     * @param components   : コンポーネント
     * @param objectMapper : ObjectMapper
     */
    private void addResponses(Components components, ObjectMapper objectMapper) {

        var badRequestContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされたパラメータは正しくありません")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail("questionは必須項目です")
                .build()
        );
        var unauthorizedContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("認証に失敗しました")
                .status(HttpStatus.UNAUTHORIZED.value())
                .detail("認証情報が付与されていません")
                .build()
        );
        var loginFailureContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("認証に失敗しました")
                .status(HttpStatus.UNAUTHORIZED.value())
                .detail("ログインに失敗しました")
                .build()
        );
        var forbiddenContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("アクセスが拒否されました")
                .status(HttpStatus.FORBIDDEN.value())
                .detail("アクセスする権限がありません")
                .build()
        );
        var quizNotFound = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされたリソースは見つかりませんでした")
                .status(HttpStatus.NOT_FOUND.value())
                .detail("クイズが見つかりませんでした")
                .build()
        );
        var quizCountNotEnough = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされたリソースは見つかりませんでした")
                .status(HttpStatus.NOT_FOUND.value())
                .detail("指定された件数分のクイズが見つかりません")
                .build()
        );
        var requestQuizNotFound = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされたリソースは見つかりませんでした")
                .status(HttpStatus.NOT_FOUND.value())
                .detail("リクエスト中のクイズは存在しません")
                .build()
        );
        var internalServerErrorContent = problemContent(objectMapper, ProblemResponse.builder()
                .title("リクエストされた操作を完了できませんでした")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .detail("データベースでエラーが発生しました")
                .build()
        );

        components.addResponses(OpenApiConstant.BAD_REQUEST, new ApiResponse()
                        .description("リクエストパラメータが不正").content(badRequestContent))
                .addResponses(OpenApiConstant.UNAUTHORIZED, new ApiResponse()
                        .description("認証情報がリクエストに付与されていない").content(unauthorizedContent))
                .addResponses(OpenApiConstant.LOGIN_FAILURE, new ApiResponse()
                        .description("ログインに失敗").content(loginFailureContent))
                .addResponses(OpenApiConstant.FORBIDDEN, new ApiResponse()
                        .description("許可されていないアクセス").content(forbiddenContent))
                .addResponses(OpenApiConstant.QUIZ_NOT_FOUND, new ApiResponse()
                        .description("クイズが見つからない").content(quizCountNotEnough))
                .addResponses(OpenApiConstant.QUIZ_NOT_ENOUGH_COUNT, new ApiResponse()
                        .description("クイズの取得件数が指定件数以下").content(quizCountNotEnough))
                .addResponses(OpenApiConstant.REQUEST_QUIZ_NOT_FOUND, new ApiResponse()
                        .description("リクエスト中のクイズが見つからない").content(requestQuizNotFound))
                .addResponses(OpenApiConstant.INTERNAL_SERVER_ERROR, new ApiResponse()
                        .description("処理が正常に終了しなかった").content(internalServerErrorContent))
                .addResponses(OpenApiConstant.INSERTED_SUCCESS, new ApiResponse()
                        .description("正常に追加できた"))
                .addResponses(OpenApiConstant.UPDATED_SUCCESS, new ApiResponse()
                        .description("正常に更新できた"))
                .addResponses(OpenApiConstant.DELETED_SUCCESS, new ApiResponse()
                        .description("正常に削除できた"));
    }

    /**
     * ProblemResponseのコンテンツを作成する
     *
     * @param objectMapper : ObjectMapper
     * @param response     : 異常時のレスポンス定義クラス
     * @return Content
     */
    private Content problemContent(ObjectMapper objectMapper, ProblemResponse response) {
        try {
            final String example = objectMapper.writeValueAsString(response);
            var schema = new Schema<ProblemResponse>().$ref("ProblemResponse");
            return new Content().addMediaType(MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    new io.swagger.v3.oas.models.media.MediaType().schema(schema).example(example));
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new IllegalStateException("Swaggerのコンテンツ作成に失敗しました", exception);
        }
    }
}
