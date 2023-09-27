package com.senla.courses;

import com.senla.courses.controller.ControllerInterface;
import com.senla.courses.controller.impl.ControllerInterfaceImpl;
import com.senla.courses.repository.DatabaseInterface;
import com.senla.courses.repository.impl.DatabaseInterfaceImpl;
import com.senla.courses.service.ServiceInterface;
import com.senla.courses.service.impl.ServiceInterfaceImpl;
import com.senla.courses.util.ParametersHolder;
import com.senla.courses.util.impl.ParametersHolderImpl;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogManager().getLogger("Main.class");
    public static void main(String[] args) {

        //Проверка архитектуры
        ParametersHolder parametersHolder = new ParametersHolderImpl();
        DatabaseInterface databaseInterface = new DatabaseInterfaceImpl(parametersHolder);
        ServiceInterface serviceInterface = new ServiceInterfaceImpl(databaseInterface);
        ControllerInterface controllerInterface = new ControllerInterfaceImpl(serviceInterface);
        controllerInterface.start();

        logger.warning("This is warn message");
    }
}
