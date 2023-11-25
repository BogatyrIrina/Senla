package com.senla.courses.repository.impl;

import com.senla.courses.entity.Trainer;
import com.senla.courses.repository.ConnectionHolder;
import com.senla.courses.repository.TrainerRepository;
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
public class TrainerRepositoryImpl implements TrainerRepository {
    private static final String FIND_BY_ID = "SELECT * from my_study.trainers WHERE id = ?;";
    private static final String FIND_ALL  = "SELECT * from my_study.trainers;";
    private static final String SAVE_TRAINER = "INSERT INTO my_study.trainers (id, name, surname, specialization) " +
            "VALUES (nextval('my_study.trainers_id_seq'), ?, ?, ?);";
    private static final String UPDATE_TRAINER = "UPDATE my_study.trainers set name=?, surname=?, specialization=? WHERE id=?";
    private static final String DELETE_TRAINER = "DELETE from my_study.trainers WHERE id=?";

    private final ConnectionHolder connectionHolder;


    @Override
    public Trainer getTrainer(Long id) {
        //трай с ресурсами после выполнения connection и ps закроются
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Trainer trainer;

            if (rs.next()) {
                trainer = new Trainer();
                trainer.setId(rs.getLong("id"));
                trainer.setName(rs.getString("name"));
                trainer.setSurname(rs.getString("surname"));
                trainer.setSpecialization(rs.getString("specialization"));
                return trainer;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL);) {

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Trainer trainer = new Trainer();
                trainer.setId(rs.getLong("id"));
                trainer.setName(rs.getString("name"));
                trainer.setSurname(rs.getString("surname"));
                trainer.setSpecialization(rs.getString("specialization"));

                trainers.add(trainer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return trainers;
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        //возвращаем id чтоб сразу его вернуть в сервис
        String columnNames[] = new String[]{"id"};
        Connection connection = connectionHolder.getConnection();


        try (PreparedStatement statement = connection.prepareStatement(SAVE_TRAINER, columnNames);) {
            statement.setString(1, trainer.getName());
            statement.setString(2, trainer.getSurname());
            statement.setString(3, trainer.getSpecialization());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating trainer failed, no rows affected.");
            }

            //заполняем trainerId
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    trainer.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating trainer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return trainer;
    }

    @Override
    public Trainer modifyTrainer(Trainer trainer) {
        Connection connection = connectionHolder.getConnection();
        //трай с ресурсами, после выполнения: connection и ps закроются
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TRAINER);) {
            statement.setString(1, trainer.getName());
            statement.setString(2, trainer.getSurname());
            statement.setString(3, trainer.getSpecialization());
            statement.setLong(4, trainer.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating trainer failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return trainer;
    }

    @Override
    public boolean deleteTrainer(Long id) {
        Connection connection = connectionHolder.getConnection();
        //трай с ресурсами, после выполнения: connection и ps закроются
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TRAINER);) {
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating trainer failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got SQL Exception " + e.getMessage());
        }
        return false;
    }
}


