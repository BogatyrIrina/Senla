package com.senla.courses.service.impl;

import com.senla.courses.repository.DatabaseInterface;
import com.senla.courses.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ServiceInterfaceImpl implements ServiceInterface {
    @Autowired
    private DatabaseInterface databaseInterface;

//    public ServiceInterfaceImpl(DatabaseInterface databaseInterface) {
//        this.databaseInterface = databaseInterface;
//    }

    @Override
    public String execute() {
        return databaseInterface.execute();
    }
}
