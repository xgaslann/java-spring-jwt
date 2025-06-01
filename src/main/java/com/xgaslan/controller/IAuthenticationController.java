package com.xgaslan.controller;

import com.xgaslan.data.models.AuthenticationModel;
import com.xgaslan.data.models.UserModel;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {

    ResponseEntity<UserModel.UserViewModel> register(AuthenticationModel.Register register);

    ResponseEntity<AuthenticationModel.LoginViewModel> login(AuthenticationModel.Login login);
}
