package com.example.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.example.backend")
@PropertySource("classpath:application.dev.yml")
public class SpringConfiguration {
}
