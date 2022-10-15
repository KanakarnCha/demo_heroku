package com.example.BackendFinalProject.exception;

public class BaseException extends Exception {

    public BaseException(String code) {
        super("Error Code : "+code);
    }
}
