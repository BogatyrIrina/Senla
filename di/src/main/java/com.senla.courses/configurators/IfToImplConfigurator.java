package com.senla.courses.configurators;

import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

public interface IfToImplConfigurator {
    Class<?> getImplClass(Class<?> ifc);
    List<Class<?>> getImplClassList(Class<?> ifc);
    //Reflections getScanner();

//    <T> Class<? extends T> getImplClass(Class<T> ifc);
//
//    Reflections getScanner();

}
