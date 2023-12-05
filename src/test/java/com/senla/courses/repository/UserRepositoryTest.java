package com.senla.courses.repository;

import com.senla.courses.config.LiquibaseMigration;
import com.senla.courses.entity.Trainer;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ComponentScan
@ContextConfiguration(classes = UserRepositoryTest.class)
@Import(value = {H2HibernateConfig.class, LiquibaseMigration.class})
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    UserRepository userRepository;
    @Test
    public void testGetById() {

        User byId = userRepository.getById(1L);

        assertEquals("Иван", byId.getName());
        assertEquals("Петров", byId.getSurname());
        assertEquals("test@mail.ru", byId.getEmail());

        List<Trainer> trainers = byId.getTrainers();

        assertEquals(4, trainers.size());

        List<Training> trainings = byId.getTrainings();

        assertEquals(1, trainings.size());
    }

    @Test
    public void testGetByEmail() {

        User byEmail = userRepository.getByEmail("irinamail@test.ru");

        assertEquals("Ирина", byEmail.getName());
        assertEquals("Иванова", byEmail.getSurname());

        List<Training> trainings = byEmail.getTrainings();

        assertEquals(2, trainings.size());

        List<Trainer> trainers = byEmail.getTrainers();

        assertEquals(1, trainers.size());
    }

}
