package com.senla.courses.repository.impl;

import com.senla.courses.Application;
import org.apache.logging.log4j.LogManager;
import com.senla.courses.repository.ConnectionHolder;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConnectionHolderImpl implements ConnectionHolder {
    private static final Logger log = LogManager.getLogger(Application.class);

    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String pass;

    private List<Connection> freeConnections = Collections.synchronizedList(new ArrayList<Connection>());
    private Map<String, Connection> usedConnections = new ConcurrentHashMap<>();

    @Override
    public Connection getConnection() {
        try {
            String currentThreadName = Thread.currentThread().getName();

            //получаем текущий коннект если есть
            Connection currentThreadConnection = usedConnections.get(currentThreadName);
            if (currentThreadConnection != null) {

                //Если коннект был открыт транзакцией и оказался закрыт,
                // то выбрасывать ошибку.
                if (currentThreadConnection.isClosed()) {
                    throw new RuntimeException("Connection был закрыт в " + currentThreadName);
                }
                log.info("возвращаем текущий connection для {} : {}", currentThreadName, currentThreadConnection);
                return currentThreadConnection;
            }

            //если есть свободный возвращаем его
            if (!freeConnections.isEmpty()) {
                Connection freeConnection = freeConnections.remove(0);
                //Если коннект уже закрыт, то открывать новый.
                if (!freeConnection.isClosed()) {
                    log.info("возвращаем свободный connection для {} : {}", currentThreadName, freeConnection);
                    usedConnections.put(currentThreadName, freeConnection);
                    return freeConnection;
                }
            }

            //если свободных нет, создаем новый
            Connection connection = DriverManager.getConnection(db_url, user, pass);
            log.info("возвращаем новый connection для {} : {}", currentThreadName, connection);
            usedConnections.put(currentThreadName, connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean releaseConnection() {
        String name = Thread.currentThread().getName();
        Connection removed = usedConnections.remove(name);
        log.info("releaseConnection для {} : {}", name, removed);
        freeConnections.add(removed);
        return true;
    }

    /**
     * Закрыть все открытые коннекты при закрытии контекста приложения.
     */
    @PreDestroy
    public void closeConnections() {

        freeConnections.forEach(c -> {
            try {
                log.info("Пытаюсь закрыть свободный connection {}", c);
                if (!c.isClosed()) {
                    c.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        usedConnections.forEach((threadName, connection) -> {
            try {
                log.info("Пытаюсь закрыть connection от {} : {}", threadName, connection);
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
