package com.elite.miko.quiz.presantation.model.response;

import com.elite.miko.quiz.domain.model.dto.ResultFetchQuizInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {

    private int questionCount;
    private List<ResultFetchQuizInfo> quizList;
}
