package com.xgaslan.controller.impl;

import com.xgaslan.controller.IAuthenticationController;
import com.xgaslan.data.models.AuthenticationModel;
import com.xgaslan.data.models.UserModel;
import com.xgaslan.services.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController implements IAuthenticationController {

    private final IAuthenticationService _authenticationService;

    @Autowired
    public AuthenticationController(IAuthenticationService authenticationService) {
        _authenticationService = authenticationService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserModel.UserViewModel> register(@Valid @RequestBody AuthenticationModel.Register register) {
        var result = _authenticationService.register(register);
        return ResponseEntity.ok(result);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthenticationModel.LoginViewModel> login(@Valid @RequestBody AuthenticationModel.Login login) {
        return null;
    }

    @GetMapping("/alive")
    public ResponseEntity<String> alive() {
        return ResponseEntity.ok("Service is alive");
    }
}
