package com.senla.courses.repository.impl;

import com.senla.courses.entity.User;
import com.senla.courses.repository.ConnectionHolder;
import com.senla.courses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private static final String FIND_BY_ID = "SELECT * from my_study.users WHERE id = ?;";
    private static final String FIND_ALL = "SELECT * from my_study.users;";
    private static final String SAVE_USER = "INSERT INTO my_study.users (id, name, surname, email) VALUES (nextval('my_study.users_id_seq'), ?, ?, ?);";
    private static final String UPDATE_USER = "UPDATE my_study.users set name=?, surname=?, email=? WHERE id=?";
    private static final String DELETE_USER = "DELETE from my_study.users WHERE id=?";

    private final ConnectionHolder connectionHolder;

    @Override
    public User getUser(Long id) {
        //трай с ресурсами после выполнения connection и ps закроются
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            User user;

            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                return user;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL);) {

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));

                users.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return users;
    }

    @Override
    public User saveUser(User user) {
        //возвращаем id чтоб сразу его вернуть в сервис
        String columnNames[] = new String[]{"id"};
        Connection connection = connectionHolder.getConnection();


        try (PreparedStatement statement = connection.prepareStatement(SAVE_USER, columnNames);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            //заполняем userId
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return user;
    }

    @Override
    public User modifyUser(User user) {
        Connection connection = connectionHolder.getConnection();
        //трай с ресурсами, после выполнения: connection и ps закроются
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setLong(4, user.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        Connection connection = connectionHolder.getConnection();
        //трай с ресурсами, после выполнения: connection и ps закроются
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER);) {
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return false;
    }

}
