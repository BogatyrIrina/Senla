package com.senla.courses.repository.impl;

import com.senla.courses.annotation.Autowire;
import com.senla.courses.repository.DatabaseInterface;
import com.senla.courses.util.ParametersHolder;

public class DatabaseInterfaceImpl implements DatabaseInterface {
    @Autowire
    private  ParametersHolder parametersHolder;
//    public DatabaseInterfaceImpl(ParametersHolder parametersHolder){
//        this.parametersHolder = parametersHolder;
//    }
    @Override
    public String execute() {

        return parametersHolder.getSomeText();
    }
}
