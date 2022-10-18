package com.example.BackendFinalProject.services;

import com.example.BackendFinalProject.entity.UserEntity;
import com.example.BackendFinalProject.exception.BaseException;
import com.example.BackendFinalProject.exception.UserException;
import com.example.BackendFinalProject.model.LoginUser;
import com.example.BackendFinalProject.model.RegisterModel;
import com.example.BackendFinalProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    public final JwtService jwtService;
    public UserService(PasswordEncoder passwordEncoder, JwtService jwtService) {

        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String createUser(RegisterModel registerModel) throws UserException {
        if (registerModel.getUsername() == null || Objects.equals(registerModel.getUsername(), "")){
            throw  UserException.registerUserFail();
        }
        if(registerModel.getFirstname() == null || Objects.equals(registerModel.getFirstname(), "")){
            throw  UserException.registerUserFail();
        }
        if(registerModel.getLastname() == null || Objects.equals(registerModel.getLastname(), "")){
            throw  UserException.registerUserFail();
        }
        if(registerModel.getPhoneNumber() == null || Objects.equals(registerModel.getPhoneNumber(), "")){
            throw  UserException.registerUserFail();
        }
        if(registerModel.getPassword() == null || Objects.equals(registerModel.getPassword(), "")){
            throw  UserException.registerUserFail();
        }
        if (userRepository.existsByUserUsername(registerModel.getUsername())){
            throw  UserException.registerUserFail();
        }

        registerModel.setPassword(passwordEncoder.encode(registerModel.getPassword()));
        UserEntity userEntity = new UserEntity();
        userEntity.setUserFirstname(registerModel.getFirstname());
        userEntity.setUserLastname(registerModel.getLastname());

        userEntity.setUserPhone(registerModel.getPhoneNumber());
        userEntity.setUserUsername(registerModel.getUsername());
        userEntity.setUserPassword(registerModel.getPassword());

        userRepository.save(userEntity);
        return "Register Success";
    }

    public String loginUser(LoginUser loginUser) throws BaseException {
        if (loginUser.getUsername() == null || Objects.equals(loginUser.getUsername(), "")){
            throw UserException.registerUserFail();
        }
        if (loginUser.getPassword() == null || Objects.equals(loginUser.getPassword(), "")){
            throw UserException.registerUserFail();
        }
        Optional<UserEntity> userEntity = userRepository.findByUserUsername(loginUser.getUsername());
        if (userEntity == null){
            throw UserException.registerUserFail();
        }
        UserEntity user  = userEntity.get();
        if (!passwordEncoder.matches(loginUser.getPassword(),user.getUserPassword())){
            throw UserException.registerUserFail();
        }

        return jwtService.createToken(user);
    }

}
