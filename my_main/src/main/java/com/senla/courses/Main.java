package com.senla.courses;

import com.senla.courses.api.Starter;
import com.senla.courses.configurators.ValueConfigurator;
import com.senla.courses.controller.ControllerInterface;
import com.senla.courses.controller.impl.ControllerInterfaceImpl;
import com.senla.courses.injection.AppContext;
import com.senla.courses.repository.DatabaseInterface;
import com.senla.courses.repository.impl.DatabaseInterfaceImpl;
import com.senla.courses.service.ServiceInterface;
import com.senla.courses.service.impl.ServiceInterfaceImpl;
import com.senla.courses.util.ParametersHolder;
import com.senla.courses.util.impl.ParametersHolderImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static java.util.logging.LogManager.*;

public class Main {
//    private static final Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) {

        //Проверка архитектуры
//        ParametersHolder parametersHolder = new ParametersHolderImpl();
//        DatabaseInterface databaseInterface = new DatabaseInterfaceImpl(parametersHolder);
//        ServiceInterface serviceInterface = new ServiceInterfaceImpl(databaseInterface);
//        ControllerInterface controllerInterface = new ControllerInterfaceImpl(serviceInterface);
//        controllerInterface.start();

//        ValueConfigurator valueConfigurator = new ValueConfigurator();
//        valueConfigurator.configure(parametersHolder, null);
//        System.out.println(parametersHolder.getSomeText());

//        logger.warn("Program starts. This is warn message");

        AppContext applicationContext = Starter.run(List.of("com.senla.courses.controller", "com.senla.courses.service",
                "com.senla.courses.repository", "com.senla.courses.util", "com.senla.courses"));

//        logger.info("Context have been Created");

        ControllerInterface controllerInterface = (ControllerInterface) applicationContext
                .getObject(ControllerInterface.class);

//        logger.info("Controller interface was extracted");

        controllerInterface.start();

//        logger.error("GOOD JOB!!!");


//        DatabaseInterface repository = applicationContext.getObject();
//        ServiceInterface serviceInterface = new ServiceInterfaceImpl(repository);
//        ControllerInterface controllerInterface1 = new ControllerInterfaceImpl(serviceInterface);

        //DatabaseInterfaceImpl
        //ParametersHolderImpl

    }
}
