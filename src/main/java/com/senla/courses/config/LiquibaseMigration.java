package com.senla.courses.config;

import com.senla.courses.repository.ConnectionHolder;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.Statement;

@RequiredArgsConstructor
@Component
public class LiquibaseMigration {
    @Value("${liquibase.change-log-file}")
    private String changeLogFilePath;
    @Value("${liquibase.change-log-schema}")
    private String changeLogSchema;
    private final ConnectionHolder dbConnection;

    /**
     * Интеграцию с Liquibase делаем на уровне бина в конфигурации.
     * Скрипты проводят update при запуске приложения, на этапе инициализации контекста
     * PostConstruct метод вызывается сразу после запуска контекста spring
     */
    @PostConstruct
    public boolean migrate() {
        Connection connection = dbConnection.getConnection();
        try (Statement statement = connection.createStatement();) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            statement.execute("SET search_path TO %s;".formatted(changeLogSchema));
            Liquibase liquibase = new Liquibase(changeLogFilePath, new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        dbConnection.releaseConnection();
        return true;
    }
}
