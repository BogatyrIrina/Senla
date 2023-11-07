package com.senla.courses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.courses.controller.FitnessCenterApp;
import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import com.senla.courses.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    public static void main(String[] args) throws JsonProcessingException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        UserController userController = context.getBean("userController", UserController.class);
        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);

        System.out.println(userController.users());

//        String userById = userController.getUserById(4L);
//        System.out.println(userById);

        System.out.println("Создание");
        UserDto userDto = new UserDto();
        userDto.setUserName("John");
        userDto.setPassword("password123");
        String newUser = userController.create(userDto);
        System.out.println(newUser);

        Long newUserId = objectMapper.readValue(newUser, UserDto.class).getId();

        System.out.println("Обновление");
        userDto.setId(newUserId);
        userDto.setUserName("John Black");
        userDto.setPassword("qwerty123");
        System.out.println(userController.update(userDto));

        System.out.println("Получение");
        System.out.println(userController.getUserById(newUserId));

        System.out.println("Удаление");
        System.out.println(userController.delete(newUserId));

        System.out.println("Удаление с false");
        System.out.println(userController.delete(555L));

        System.out.println("Получить всех пользователей");
        System.out.println(userController.users());
    }
}
