package com.senla.courses.configurators;

import com.senla.courses.annotation.Autowire;
import com.senla.courses.annotation.Component;
import com.senla.courses.annotation.Value;
import com.senla.courses.injection.AppContext;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class IfcToImplConfiguratorJavaImpl implements IfToImplConfigurator {
    @Getter
    private Reflections scanner;
    private Map<Class, Class> ifc2ImplClass;

    public IfcToImplConfiguratorJavaImpl(List<String> packages, Map<Class, Class> ifc2ImplClass) {
        this.scanner = new Reflections(packages);

        this.ifc2ImplClass = new HashMap<>(ifc2ImplClass);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> interfaceType) {
        return ifc2ImplClass.computeIfAbsent(interfaceType, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(interfaceType);

            if (classes.size() != 1) {
                throw new RuntimeException("found [%s] impl of [%s]".formatted(classes.size(), interfaceType.getName()));
            }

            return classes.iterator().next();
        });
    }
}
