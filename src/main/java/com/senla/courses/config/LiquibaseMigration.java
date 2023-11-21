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
    private final ConnectionHolder dbConnection;

    @PostConstruct
    public boolean migrate() {
        Connection connection = dbConnection.getConnection();
        try (Statement statement = connection.createStatement();) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase(changeLogFilePath, new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
