package com.example.BackendFinalProject.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("USER " + code);
    }
    public static UserException registerUserFail(){
        return new UserException("Register Fail");
    }
}
