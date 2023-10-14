package com.senla.courses.injection;

import com.senla.courses.annotation.PostConstruct;
import com.senla.courses.configurators.BeanConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BeanFactory {
    private final AppContext context;
    private List<BeanConfigurator> configurators = new ArrayList<>();
    @SneakyThrows
    public BeanFactory(AppContext context) {
        this.context = context;
        for (Class aClass : context.getConfig().getImplClassList(BeanConfigurator.class)) {
            Class<BeanConfigurator> objectConfigurator = aClass;
            configurators.add(objectConfigurator.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);
        configure(t);
        invokeInit(implClass, t);
//        t = wrapWithProxyIfNeeded(implClass, t);
        return t;

    }

    private <T> void invokeInit(Class<T> implClass, T t)
            throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(b -> b.configure(t,context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException,
            IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
