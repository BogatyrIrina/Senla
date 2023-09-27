package com.senla.courses.repository.impl;

import com.senla.courses.repository.DatabaseInterface;
import com.senla.courses.util.ParametersHolder;

public class DatabaseInterfaceImpl implements DatabaseInterface {
    private final ParametersHolder parametersHolder;
    public DatabaseInterfaceImpl(ParametersHolder parametersHolder){
        this.parametersHolder = parametersHolder;
    }
    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}
