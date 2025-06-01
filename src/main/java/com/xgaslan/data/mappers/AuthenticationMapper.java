package com.xgaslan.data.mappers;

import com.xgaslan.data.entities.User;
import com.xgaslan.data.models.AuthenticationModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationMapper {
    public static AuthenticationModel.LoginViewModel toLoginViewModel(User user) {
        if (user == null) {
            return null;
        }

        return new AuthenticationModel.LoginViewModel(

        );
    }
}
