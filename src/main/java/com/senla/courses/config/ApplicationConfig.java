package com.senla.courses.config;

import com.senla.courses.service.UserService;
import com.senla.courses.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.senla.courses")
public class ApplicationConfig {
    // Здесь вы можете определить бины и другие настройки контекста
    // Например, можно определить бины для репозиториев, сервисов и других классов
    // используемых в вашем приложении
    // Пример:

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
