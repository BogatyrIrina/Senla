package com.senla.courses.repository.impl;

import com.senla.courses.repository.DatabaseInterface;
import com.senla.courses.util.ParametersHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInterfaceImpl implements DatabaseInterface {
    @Autowired
    private  ParametersHolder parametersHolder;

    @Override
    public String execute() {

        return parametersHolder.getSomeText();
    }
}
