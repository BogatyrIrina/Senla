package com.senla.courses.configurators;

import com.senla.courses.injection.AppContext;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class AutowireConfigurator implements BeanConfigurator {
    @Getter
    private Reflections scanner;
    private Map<Class, Class> ifc2ImplClass;

    public AutowireConfigurator(String packagesToScan, Map<Class, Class> ifc2ImplClass) {
        this.ifc2ImplClass = ifc2ImplClass;
        this.scanner = new Reflections(packagesToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                throw new RuntimeException(ifc + " has 0 or more than one impl please update your config");
            }

            return classes.iterator().next();
        });

//    @Override
//    public Reflections getScanner() {
//        return null;
//    }

    //A -> B, C -> B, B - > D
    //
//    @Override
//    @SneakyThrows
//    public void configure(Object t, AppContext appContext) {
//        for (Field field : t.getClass().getDeclaredFields()) {
//            if (field.isAnnotationPresent(Autowire.class)) {
//                field.setAccessible(true);
//                Object object = appContext.getObject(field.getType());
//                field.set(t, object);
//            }
//        }
    }

//    @Override
//    public Reflections getScanner() {
//        return null;
//    }

    @Override
    public void configure(Object t, AppContext context) {

    }
}
