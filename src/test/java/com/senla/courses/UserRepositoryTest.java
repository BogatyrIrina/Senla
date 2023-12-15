package com.senla.courses;

import com.senla.courses.entity.Trainer;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import com.senla.courses.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration("classpath:META-INF/web-resources")
@ContextHierarchy(@ContextConfiguration(classes = Application.class))
@Transactional
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
