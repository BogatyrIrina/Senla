package com.senla.courses.api;

import com.senla.courses.configurators.AutowireConfigurator;
import com.senla.courses.configurators.IfToImplConfigurator;
import com.senla.courses.configurators.IfcToImplConfiguratorJavaImpl;
import com.senla.courses.injection.AppContext;
import com.senla.courses.injection.BeanFactory;


import java.util.List;
import java.util.Map;

public class Starter {
    public static AppContext run(List<String> packagesToScan, Map<Class, Class> ifc2ImplClass) {
        IfToImplConfigurator cfg = new IfcToImplConfiguratorJavaImpl(packagesToScan, ifc2ImplClass);
        AppContext context = new AppContext(cfg);
        BeanFactory factory = new BeanFactory(context);
        context.setFactory(factory);
        return context;
    }
}

