package com.senla.courses.controller.impl;

import com.senla.courses.controller.ControllerInterface;
import com.senla.courses.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class ControllerInterfaceImpl implements ControllerInterface {
    @Autowired
    private ServiceInterface serviceInterface;
    private String appender;

    public ControllerInterfaceImpl(ServiceInterface serviceInterface) {

        this.serviceInterface = serviceInterface;
    }

    @PostConstruct
    public void init() {
        appender = " Awesome!";
    }

    @Override
    public void start() {
        System.out.println(serviceInterface.execute() + appender);
    }
}
