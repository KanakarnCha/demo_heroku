package com.example.BackendFinalProject.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@ControllerAdvice
public class ErrorAdvisor {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorModel> resError(Exception e){
        ErrorModel errorModel = ErrorModel.builder().errorText(e.getMessage())
                .errorCode(HttpStatus.EXPECTATION_FAILED.value()).build();
        return new ResponseEntity<ErrorModel>(errorModel,HttpStatus.EXPECTATION_FAILED);
    }
    @Data
    @Builder
    static class ErrorModel{
        private String errorText;
        private int errorCode;
        private final LocalDateTime errorTime = LocalDateTime.now(ZoneId.of("UTC+07:00"));
    }
}
