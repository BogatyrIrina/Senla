package com.senla.courses.controller.impl;

import com.senla.courses.controller.ControllerInterface;
import com.senla.courses.service.ServiceInterface;

public class ControllerInterfaceImpl implements ControllerInterface {
    private final ServiceInterface serviceInterface;

    public ControllerInterfaceImpl(ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public void start() {
        System.out.println(serviceInterface.execute());
    }
}
