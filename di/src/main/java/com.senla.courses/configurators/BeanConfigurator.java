package com.senla.courses.configurators;

import com.senla.courses.injection.AppContext;
import lombok.SneakyThrows;

public interface BeanConfigurator {
    @SneakyThrows
    void configure(Object t, AppContext context);
}
