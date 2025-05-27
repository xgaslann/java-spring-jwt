package com.xgaslan.starter;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        SpringApplication.run(Starter.class, args);
    }

}
