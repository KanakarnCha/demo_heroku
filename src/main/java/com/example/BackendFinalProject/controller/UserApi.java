package com.example.BackendFinalProject.controller;

import com.example.BackendFinalProject.business.UserBusiness;
import com.example.BackendFinalProject.entity.UserEntity;
import com.example.BackendFinalProject.exception.BaseException;
import com.example.BackendFinalProject.exception.UserException;
import com.example.BackendFinalProject.model.LoginUser;
import com.example.BackendFinalProject.model.RegisterModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/control/api/user")
public class UserApi {
    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/register_user")
    public ResponseEntity<ResRegister> registerUser(@RequestBody RegisterModel registerModel) throws BaseException {
        ResRegister resRegister = new ResRegister();
        resRegister.setMassageReg(userBusiness.createUser(registerModel));
        return new ResponseEntity<ResRegister>(resRegister, HttpStatus.OK);
    }
    @PostMapping("/user/login")
    private ResponseEntity<Token> loginUser(@RequestBody LoginUser loginUser) throws BaseException {
        Token token = new Token();
        token.setToken(userBusiness.loginUser(loginUser));
        return new ResponseEntity<Token>(token,HttpStatus.OK);
    }
    @Data
    static class Token{
        String token;
    }
    @Data
    static class ResRegister{
        String massageReg;
    }
}
