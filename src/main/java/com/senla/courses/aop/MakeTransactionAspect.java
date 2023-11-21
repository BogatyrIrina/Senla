package com.senla.courses.aop;

import com.senla.courses.repository.ConnectionHolder;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
@RequiredArgsConstructor
public class MakeTransactionAspect {
    private final ConnectionHolder connectionHolder;


    @Around("@annotation(com.senla.courses.aop.Transaction)")
    public Object makeTransactional(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //получаем коннект
        Connection connection = connectionHolder.getConnection();
        connection.setAutoCommit(false);
        try {
            //выполняется запрос
            Object result = proceedingJoinPoint.proceed();

            connection.commit();
            return result;
        } catch (SQLException e) {
            try {
                connection.rollback();
                connectionHolder.releaseConnection();
            } catch (SQLException ex) {
                //ignore
            }
            throw new RuntimeException(e);
        } finally {
            connectionHolder.releaseConnection();
        }
    }
}
