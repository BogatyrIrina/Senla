package com.senla.courses.repository;

import java.sql.Connection;

public interface ConnectionHolder {
    Connection getConnection();
    boolean releaseConnection();
}
