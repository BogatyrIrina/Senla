package com.senla.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.courses.service.SerDesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@ComponentScan
public class Application {
    private static final Logger log = LogManager.getLogger(Application.class);
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        UserController userController = context.getBean(UserController.class);
        SerDesService serDesService = context.getBean(SerDesService.class);

        log.info(userController.users());

        UserDto userById = userController.getUserById(4L);
        log.info(serDesService.serialize(userById));

        log.info("Создание");
        UserDto userDto = new UserDto();
        userDto.setUserName("John");
        userDto.setPassword("password123");
        UserDto newUser = userController.create(userDto);
        log.info(serDesService.serialize(newUser));

        Long newUserId = newUser.getId();


        log.info("Обновление");
        userDto.setId(newUserId);
        userDto.setUserName("John Black");
        userDto.setPassword("qwerty123");
        log.info(serDesService.serialize(userController.update(userDto)));

        log.info("Получение");
        log.info(serDesService.serialize(userController.getUserById(newUserId)));

        log.info("Удаление");
        log.info(userController.delete(newUserId));

//        log.info("Удаление с false");
//        log.info(userController.delete(555L));

        log.info("Получить всех пользователей");
        log.info(serDesService.serialize(userController.users()));
    }
}
