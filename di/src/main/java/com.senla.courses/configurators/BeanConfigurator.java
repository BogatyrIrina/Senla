package com.senla.courses.configurators;

import com.senla.courses.injection.AppContext;
import lombok.SneakyThrows;
import org.reflections.Reflections;

public interface BeanConfigurator {
    @SneakyThrows
    void configure(Object t, AppContext context);
}
