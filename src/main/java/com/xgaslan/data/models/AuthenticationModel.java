package com.xgaslan.data.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthenticationModel {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login{
        private String username;

        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginViewModel{
        private String token;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Register{
        @NotEmpty
        private String username;

        @NotEmpty
        private String password;
    }
}
