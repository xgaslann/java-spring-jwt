package com.xgaslan.services.impl;

import com.xgaslan.data.mappers.UserMapper;
import com.xgaslan.data.models.AuthenticationModel;
import com.xgaslan.data.models.UserModel;
import com.xgaslan.repositories.IUserRepository;
import com.xgaslan.security.config.AppConfig;
import com.xgaslan.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserRepository _userRepository;
    private final AppConfig _appConfig;

    @Autowired
    public AuthenticationService(IUserRepository userRepository, AppConfig appConfig) {
        _userRepository = userRepository;
        _appConfig = appConfig;
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
}
