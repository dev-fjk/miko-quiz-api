package com.elite.miko.quiz.domain.model.consts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.Getter;
import org.seasar.doma.Domain;

@Getter
@Domain(valueType = String.class, factoryMethod = "of")
@Schema(enumAsRef = true, description = "クイズの状態: \n"
        + "* `enabled` - 有効\n"
        + "* `request` - 追加リクエスト中\n"
        + "* `disabled` - 無効\n"
        + "* `ng` - チェックNG\n"
)
public enum QuizStatus {

    ENABLED("enabled"),
    REQUEST("request"),
    DISABLED("disabled"),
    NG("ng"),
    INVALID("invalid");

    private final String value;

    QuizStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * JsonからEnumへの変換に使用されるファクトリメソッド
     *
     * @param value : Json側の値
     * @return 値がValueと一致した場合インスタンス作成し返却
     */
    @JsonCreator
    public static QuizStatus create(String value) {

        if (Objects.isNull(value)) {
            return null;
        }

        return Stream.of(values())
                .filter(v -> value.equals(v.getValue()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("quizStatusが異常な値です")
                );
    }

    /**
     * DBからEntityへの変換に使用されるファクトリメソッド
     *
     * @param value : DB側の値
     * @return 値がValueと一致した場合インスタンス作成し返却
     */
    public static QuizStatus of(String value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("quizStatusが設定されていません");
        }

        return Stream.of(values())
                .filter(v -> value.equals(v.getValue()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("quizStatusが異常な値です")
                );
    }
}
