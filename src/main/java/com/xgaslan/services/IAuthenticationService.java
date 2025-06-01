package com.xgaslan.services;

import com.xgaslan.data.models.AuthenticationModel;
import com.xgaslan.data.models.UserModel;

public interface IAuthenticationService {
    UserModel.UserViewModel register(AuthenticationModel.Register model);

    AuthenticationModel.LoginViewModel login(AuthenticationModel.Login model);


}
