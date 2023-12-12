package com.senla.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan
public class Application {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
