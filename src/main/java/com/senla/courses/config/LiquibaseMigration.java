package com.senla.courses.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Component
public class LiquibaseMigration {
    @Value("${liquibase.change-log-file}")
    private String changeLogFilePath;
    @Value("${liquibase.change-log-schema}")
    private String changeLogSchema;
    private final DataSource dataSource;

    /**
     * Интеграцию с Liquibase делаем на уровне бина в конфигурации.
     * Скрипты проводят update при запуске приложения, на этапе инициализации контекста
     * PostConstruct метод вызывается сразу после запуска контекста spring
     */
    @Bean
    public SpringLiquibase migrate() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLogFilePath);
        liquibase.setLiquibaseSchema(changeLogSchema);
        return liquibase;
    }
}
