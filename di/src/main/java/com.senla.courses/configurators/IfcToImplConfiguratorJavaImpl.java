package com.senla.courses.configurators;

import com.senla.courses.annotation.Component;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

public class IfcToImplConfiguratorJavaImpl implements IfToImplConfigurator {
    @Getter
    private Reflections scanner;
    private Map<Class, Class> ifc2ImplClass;

    public IfcToImplConfiguratorJavaImpl(List<String> packages) {
        this.scanner = new Reflections(packages);

        this.ifc2ImplClass = new HashMap<>();
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> interfaceType) {
        return ifc2ImplClass.computeIfAbsent(interfaceType, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(interfaceType);

            if (classes.size() != 1) {
                classes = classes.stream()
                        .filter(c -> c.isAnnotationPresent(Component.class))
                        .collect(Collectors.toSet());
                if (classes.size() != 1) {
                    throw new RuntimeException("found [%s] impl of [%s]".formatted(classes.size(), interfaceType.getName()));
                }
            }

            return classes.iterator().next();
        });
    }

}
