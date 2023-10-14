package com.senla.courses.configurators;

import com.senla.courses.injection.AppContext;
import lombok.SneakyThrows;
import org.reflections.Reflections;

public interface BeanConfigurator {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();

    @SneakyThrows
    void configure(Object t, AppContext context);
}
