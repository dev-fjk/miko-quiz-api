package com.elite.miko.quiz.presantation.controller;

import com.elite.miko.quiz.domain.model.entity.Answer;
import com.elite.miko.quiz.domain.service.AnswerService;
import com.elite.miko.quiz.presantation.model.response.AnswerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @GetMapping("/answer/")
    public ResponseEntity<?> get() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<AnswerResponse> responses = new ArrayList<>();
        List<Answer> answerList = answerService.fetchAll();
        log.info("jsonStr : {}", objectMapper.writeValueAsString(answerList));
        for (Answer answer : answerList) {
            responses.add(this.modelMapper.map(answer, AnswerResponse.class));
        }
        return new ResponseEntity<>(responses, httpHeaders, HttpStatus.OK);
    }
}
