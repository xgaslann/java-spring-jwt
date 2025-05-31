package com.xgaslan.data.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {

    private String secret;
    private long expirationTimeMillis; // in milliseconds
//    private String issuer;
//    private String audience;
//    private long refreshExpirationTime; // in milliseconds

}
