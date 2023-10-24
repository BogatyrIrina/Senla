package com.senla.courses.config;

import com.fasterxml.jackson.core.util.JacksonFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
//    @Bean
    public JacksonFeature jacksonFeature;
}
