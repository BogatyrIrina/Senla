package com.senla.courses.configurators;

import com.senla.courses.injection.AppContext;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

public interface IfToImplConfigurator {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();

}
