package com.senla.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.courses.controller.TrainerController;
import com.senla.courses.controller.TrainingController;
import com.senla.courses.dto.TrainerDto;
import com.senla.courses.dto.TrainingDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Application {
    private static final Logger log = LogManager.getLogger(Application.class);

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        UserController userController = context.getBean(UserController.class);
        TrainerController trainerController = context.getBean(TrainerController.class);
        TrainingController trainingController = context.getBean(TrainingController.class);

        //fetch Named Entity Graph
        TrainingDto trainingById = trainingController.getTrainingById(1L);

        //fetch Dynamic Entity Graph
        TrainerDto trainerById = trainerController.getTrainerById(2L);

        //fetch в Criteria API
        UserDto userById = userController.getUserById(1L);

        //запрос  fetch в JPQL
        UserDto userByEmail = userController.getUserByEmail("sergeymail@test.ru");

        context.close();
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new RuntimeException("expected %s, but got %s".formatted(expected, actual));
        }
    }
}

