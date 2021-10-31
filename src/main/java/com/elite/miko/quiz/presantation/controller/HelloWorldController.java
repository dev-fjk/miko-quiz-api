package com.elite.miko.quiz.presantation.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/miko/v1/")
@RestController
public class HelloWorldController {

    @GetMapping("/hello/")
    public ResponseEntity<?> get() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>("Hello World!", httpHeaders, HttpStatus.OK);
    }
}
