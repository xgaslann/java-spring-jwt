package com.xgaslan.services.impl;

import com.xgaslan.data.mappers.AuthenticationMapper;
import com.xgaslan.data.mappers.UserMapper;
import com.xgaslan.data.models.AuthenticationModel;
import com.xgaslan.data.models.UserModel;
import com.xgaslan.repositories.IUserRepository;
import com.xgaslan.security.config.AppConfig;
import com.xgaslan.security.config.SecurityConfig;
import com.xgaslan.security.jwt.JwtService;
import com.xgaslan.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserRepository _userRepository;
    private final AppConfig _appConfig;
    private final SecurityConfig _securityConfig;

    private final JwtService _jwtService;

    @Autowired
    public AuthenticationService(IUserRepository userRepository, AppConfig appConfig, SecurityConfig securityConfig, JwtService jwtService) {
        _userRepository = userRepository;
        _appConfig = appConfig;
        _securityConfig = securityConfig;
        _jwtService = jwtService;
    }

    @Override
    public UserModel.UserViewModel register(AuthenticationModel.Register model) {
        var user = UserMapper.toUser(model, _appConfig.passwordEncoder());
        try {
            _userRepository.save(user);
        }
        catch (Exception e) {
            // Handle exception, e.g., user already exists
            throw new RuntimeException("User registration failed: " + e.getMessage());
        }
        return UserMapper.toUserViewModel(user);
    }

    @Override
    public AuthenticationModel.LoginViewModel login(AuthenticationModel.Login model) {
        try{
            var auth = _securityConfig.authenticationProvider()
                    .authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));

            if (auth.isAuthenticated()) {
                var user = _userRepository.findByUsername(model.getUsername())
                        .orElseThrow(() -> new RuntimeException("User not found"));

                var token = _jwtService.generateToken(user);

                return new AuthenticationModel.LoginViewModel(token);
            }
        }
        catch (Exception e) {
            // Handle login failure, e.g., user not found or password mismatch
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
        return null;
    }


}
