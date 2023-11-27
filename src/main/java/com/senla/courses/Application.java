package com.senla.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;


import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
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
        Collection<UserDto> usersBeforeTest = userController.users();
//        assertEquals(4, usersBeforeTest.size());

        int size = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(size);

        try {
            for (int i = 0; i < size; i++) {
                int finalI = i;
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        UserDto userDto = new UserDto();
                        userDto.setUserName("John " + finalI);
                        userDto.setUserEmail("John@mail.com");
                        userDto.setPassword("password123");
                        UserDto newUser = userController.create(userDto);
                        countDownLatch.countDown();
                    }
                };
                executorService.submit(task);
            }
            countDownLatch.await();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        Collection<UserDto> usersAfterTest = userController.users();
        assertEquals(size + usersBeforeTest.size(), usersAfterTest.size());

        log.info(userController.users());
        UserDto userById = userController.getUserById(4L);
        log.info(userById);

        log.info("Создание");
        UserDto userDto = new UserDto();
        userDto.setUserName("John");
        userDto.setUserEmail("John@mail.com");
        userDto.setPassword("password123");
        UserDto newUser = userController.create(userDto);
        log.info(newUser);

        Long newUserId = newUser.getId();

        log.info("Обновление");
        userDto.setId(newUserId);
        userDto.setUserName("John Black");
        userDto.setPassword("qwerty123");
        log.info(userController.update(userDto));

        log.info("Получение");
        log.info(userController.getUserById(newUserId));

        log.info("Удаление");
        log.info(userController.delete(newUserId));

        try {
            log.info("Поиск кого удалили");
            log.info(userController.getUserById(newUserId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        try {
            log.info("Удаление с false");
            log.info(userController.delete(555L));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        log.info("Получить всех пользователей");
        Collection<UserDto> users = userController.users();
        log.info(users);

//        вызываем закрытие всех connections через @preDestroy аннотацию
//        Закрыть все открытые коннекты при закрытии контекста приложения.
        context.close();

        log.info("done");
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new RuntimeException("expected %s, but got %s".formatted(expected, actual));
        }
    }
}

