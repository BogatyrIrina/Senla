package com.senla.courses.api;

import com.senla.courses.configurators.IfToImplConfigurator;
import com.senla.courses.configurators.IfcToImplConfiguratorJavaImpl;
import com.senla.courses.injection.AppContext;
import com.senla.courses.injection.BeanFactory;


import java.util.List;

public class Starter {
    public static AppContext run(List<String> packagesToScan) {
        IfToImplConfigurator cfg = new IfcToImplConfiguratorJavaImpl(packagesToScan);
        AppContext context = new AppContext(cfg);
        BeanFactory factory = new BeanFactory(context);
        context.setFactory(factory);
        return context;
    }
}

