package com.senla.courses.configurators;

import com.senla.courses.annotation.Autowire;
import com.senla.courses.injection.AppContext;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class AutowireConfigurator implements BeanConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, AppContext appContext) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowire.class)) {
                field.setAccessible(true);
                Object object = appContext.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
