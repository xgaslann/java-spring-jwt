package com.xgaslan.starter;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.xgaslan.data.entities")
@EnableJpaRepositories(basePackages = "com.xgaslan.repositories")
@ComponentScan(basePackages = "com.xgaslan")
@SpringBootApplication(scanBasePackages = "com.xgaslan")
public class Starter {

    public static void main(String[] args) {
        Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        SpringApplication.run(Starter.class, args);
    }

}
