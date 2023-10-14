package com.senla.courses.injection;

import com.senla.courses.configurators.IfToImplConfigurator;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class AppContext {
    public void setFactory(BeanFactory factory) {
        this.factory = factory;
    }

    private BeanFactory factory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private IfToImplConfigurator config;

    public AppContext(IfToImplConfigurator config) {
        this.config = config;
    }

    public Object getObject(Class type) {
        if (cache.containsKey(type)) {
            return cache.get(type);
        }
        Class<?> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        Object t = factory.createObject(implClass);
//        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
//        }
        return t;
    }

}
