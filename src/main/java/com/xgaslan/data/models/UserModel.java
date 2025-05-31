package com.xgaslan.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class UserModel {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserViewModel{
        private UUID id;

        private String username;
    }
}
