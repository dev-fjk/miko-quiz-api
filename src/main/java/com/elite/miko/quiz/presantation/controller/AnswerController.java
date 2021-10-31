package com.elite.miko.quiz.presantation.controller;

import com.elite.miko.quiz.domain.model.Answer;
import com.elite.miko.quiz.domain.service.AnswerService;
import com.elite.miko.quiz.presantation.model.AnswerResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/miko/v1/")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/answer/")
    public ResponseEntity<?> get() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<AnswerResponse> responses = new ArrayList<>();

        List<Answer> answerList = answerService.fetchAll();
        ModelMapper mapper = new ModelMapper();
        for (Answer answer : answerList) {
            responses.add(mapper.map(answer, AnswerResponse.class));
        }
        return new ResponseEntity<>(responses, httpHeaders, HttpStatus.OK);
    }
}
