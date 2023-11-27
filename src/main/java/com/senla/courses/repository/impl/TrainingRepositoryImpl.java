package com.senla.courses.repository.impl;

import com.senla.courses.entity.Training;
import com.senla.courses.repository.ConnectionHolder;
import com.senla.courses.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrainingRepositoryImpl implements TrainingRepository {
    private static final String FIND_BY_ID = "SELECT * from my_study.trainings WHERE id = ?;";
    private static final String FIND_ALL = "SELECT * from my_study.trainings;";
    private static final String SAVE_TRAINING = "INSERT INTO my_study.trainings (id, name, time) VALUES (nextval('my_study.trainings_id_seq'), ?, ?, ?);";
    private static final String UPDATE_TRAINING = "UPDATE my_study.trainings set name=?, time=? WHERE id=?";
    private static final String DELETE_TRAINING = "DELETE from my_study.trainings WHERE id=?";

    private final ConnectionHolder connectionHolder;

    @Override
    public Training getTraining(Long id) {
        //трай с ресурсами после выполнения connection и ps закроются
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Training training;

            if (rs.next()) {
                training = new Training();
                training.setId(rs.getLong("id"));
                training.setName(rs.getString("name"));
                training.setTime(rs.getString("time"));
                return training;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Training> getAllTrainings() {
        List<Training> trainings = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL);) {

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Training training = new Training();
                training.setId(rs.getLong("id"));
                training.setName(rs.getString("name"));
                training.setTime(rs.getString("time"));

                trainings.add(training);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return trainings;
    }

    @Override
    public Training saveTraining(Training training) {
        //возвращаем id чтоб сразу его вернуть в сервис
        String columnNames[] = new String[]{"id"};
        Connection connection = connectionHolder.getConnection();


        try (PreparedStatement statement = connection.prepareStatement(SAVE_TRAINING, columnNames);) {
            statement.setString(1, training.getName());
            statement.setString(2, training.getTime());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating training failed, no rows affected.");
            }

            //заполняем trainerId
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    training.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating training failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return training;
    }

    @Override
    public Training modifyTraining(Training training) {
        Connection connection = connectionHolder.getConnection();
        //трай с ресурсами, после выполнения: connection и ps закроются
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TRAINING);) {
            statement.setString(1, training.getName());
            statement.setString(2, training.getTime());
            statement.setLong(3, training.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating training failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return training;
    }

    @Override
    public boolean deleteTraining(Long id) {
        Connection connection = connectionHolder.getConnection();
        //трай с ресурсами, после выполнения: connection и ps закроются
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TRAINING);) {
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating training failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return false;
    }
}
