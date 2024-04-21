package com.example.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelperBeanProvider {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
