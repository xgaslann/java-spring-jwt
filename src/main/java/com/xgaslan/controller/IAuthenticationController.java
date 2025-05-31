package com.xgaslan.controller;

import com.xgaslan.data.models.AuthenticationModel;
import com.xgaslan.data.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationController {

    ResponseEntity<UserModel.UserViewModel> register(@Valid @RequestBody AuthenticationModel.Register register);

    ResponseEntity<AuthenticationModel.LoginViewModel> login(@Valid @RequestBody AuthenticationModel.Login login);
}
