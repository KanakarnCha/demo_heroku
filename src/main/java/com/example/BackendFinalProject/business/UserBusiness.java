package com.example.BackendFinalProject.business;

import com.example.BackendFinalProject.entity.UserEntity;
import com.example.BackendFinalProject.exception.BaseException;
import com.example.BackendFinalProject.exception.UserException;
import com.example.BackendFinalProject.model.LoginUser;
import com.example.BackendFinalProject.model.RegisterModel;
import com.example.BackendFinalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {
    @Autowired
    private UserService userService;
    public String createUser(RegisterModel registerModel) throws BaseException {
        return userService.createUser(registerModel);
    }
    public String loginUser(LoginUser loginUser) throws BaseException {
        return userService.loginUser(loginUser);
    }
}
