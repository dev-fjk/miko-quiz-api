package com.elite.miko.quiz.presentation.validator;

import com.elite.miko.quiz.application.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class QuizIdListValidator {

    /**
     * クイズID一覧の入力チェック用の自作バリデータ
     * 異常値の場合はValidationExceptionをThrowする
     *
     * @param quizIdList : クイズID一覧
     * @throws ValidationException バリデーションエラー時例外
     */
    public void validate(List<Long> quizIdList) throws ValidationException {

        if (CollectionUtils.isEmpty(quizIdList)) {
            throw new ValidationException("quizIdが設定されていません");
        }

        final List<String> validationErrors = new ArrayList<>();
        if (quizIdList.size() >= 50) {
            validationErrors.add("クイズIDは最大50件です");
        }
        if (quizIdList.contains(null)) {
            validationErrors.add("ID一覧にnullが含まれています");
        }

        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ValidationException(validationErrors);
        }
    }
}
