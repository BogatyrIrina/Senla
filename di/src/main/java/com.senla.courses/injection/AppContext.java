package com.senla.courses.injection;

import com.senla.courses.configurators.IfToImplConfigurator;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class AppContext {
    @Setter
    private BeanFactory factory;
    @Getter
    private IfToImplConfigurator config;

    public AppContext(IfToImplConfigurator config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        return factory.createObject(implClass);
    }
}
