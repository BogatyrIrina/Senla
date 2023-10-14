package com.senla.courses.configurators;

import com.senla.courses.annotation.Value;
import lombok.Getter;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class IfcToImplConfiguratorJavaImpl implements IfToImplConfigurator {

    private final Set<Class<?>> classes = new HashSet<>();

    public IfcToImplConfiguratorJavaImpl(List<String> packagesToScan) {
        packagesToScan.forEach(packageName -> {
            Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
            classes.addAll(reflections.getSubTypesOf(Object.class));
        });
    }

    @Override
    public Class<?> getImplClass(Class<?> ifc) {
        Set<Class> classes = this.classes.stream()
                .filter(ifc::isAssignableFrom)
                .collect(Collectors.toSet());

        if (classes.size() != 1) {
            throw new RuntimeException(ifc + " has 0 or more than one impl please update your config");
        }
        return classes.iterator().next();
    }


    @Override
    public List<Class<?>> getImplClassList(Class<?> ifc) {
        return this.classes.stream()
                .filter(ifc::isAssignableFrom)
                .collect(Collectors.toList());
    }

}
