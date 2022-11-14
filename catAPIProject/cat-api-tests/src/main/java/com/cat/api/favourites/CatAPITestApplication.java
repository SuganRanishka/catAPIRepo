package com.cat.api.favourites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cat.api"})
public class CatAPITestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatAPITestApplication.class, args);
    }
}
