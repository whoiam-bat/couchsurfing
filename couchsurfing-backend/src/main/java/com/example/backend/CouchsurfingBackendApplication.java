package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class CouchsurfingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouchsurfingBackendApplication.class, args);
    }

}
