package com.senla.courses.service.impl;

import com.senla.courses.annotation.Autowire;
import com.senla.courses.repository.DatabaseInterface;
import com.senla.courses.service.ServiceInterface;

public class ServiceInterfaceImpl implements ServiceInterface {
    @Autowire
    private DatabaseInterface databaseInterface;

//    public ServiceInterfaceImpl(DatabaseInterface databaseInterface) {
//        this.databaseInterface = databaseInterface;
//    }

    @Override
    public String execute() {
        return databaseInterface.execute();
    }
}
