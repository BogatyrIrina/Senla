package com.senla.courses;

import com.senla.courses.config.ApplicationConfig;
import com.senla.courses.controller.FitnessCenterApp;
import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import com.senla.courses.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserController userController = context.getBean("userController", UserController.class);
        for (UserDto userDto : userController.users()) {
            System.out.println(userDto);
        }
        userController.getUserById(4);
        userController.save()
//        FitnessCenterApp fitnessCenterApp = context.getBean("fitnessCenterAppImpl", FitnessCenterApp.class);
//        fitnessCenterApp.run();

//        UserService userService = context.getBean(UserService.class);
    }


//        AppContext applicationContext = Starter.run(List.of("org.example.controller", "org.example.service",
//                "org.example.repository", "org.example.util"));
//
//
//        ControllerInterface controllerInterface = (ControllerInterface) applicationContext
//                .getObject(ControllerInterface.class);
//
//
//        controllerInterface.start();

}
