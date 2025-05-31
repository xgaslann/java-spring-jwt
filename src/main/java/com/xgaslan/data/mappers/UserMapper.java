package com.xgaslan.data.mappers;

import com.xgaslan.data.entities.User;
import com.xgaslan.data.models.AuthenticationModel;
import com.xgaslan.data.models.UserModel;
import com.xgaslan.security.config.AppConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    private final AppConfig _appConfig;

    public UserMapper(AppConfig appConfig) {
        _appConfig = appConfig;
    }

    public static UserModel.UserViewModel toUserViewModel(User entity) {
        if (entity == null) {
            return null;
        }

        return new UserModel.UserViewModel(entity.getId(), entity.getUsername());
    }

    public static User toUser(AuthenticationModel.Register model, BCryptPasswordEncoder passwordEncoder) {
        if (model == null) {
            return null;
        }

        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(passwordEncoder.encode(model.getPassword())); // Password should be hashed in a real application
        return user;
    }
}
