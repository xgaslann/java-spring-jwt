package com.xgaslan.springjwt;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJwtApplication {

    public static void main(String[] args) {
        Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        SpringApplication.run(SpringJwtApplication.class, args);
    }

}
