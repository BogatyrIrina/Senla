package com.senla.courses.configurators;

import org.reflections.Reflections;


public interface IfToImplConfigurator {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();

}
