package com.senla.courses.configurators;

import com.senla.courses.annotation.Autowire;
import com.senla.courses.annotation.Component;
import com.senla.courses.injection.AppContext;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

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
