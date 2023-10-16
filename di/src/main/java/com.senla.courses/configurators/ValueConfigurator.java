package com.senla.courses.configurators;

import com.senla.courses.annotation.Value;
import com.senla.courses.injection.AppContext;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ValueConfigurator implements BeanConfigurator {
    public static final String PROPERTIES_FILE_NAME = "application.properties";
    public static final Map<String, String> propertyValueMap = new HashMap<>();

    public ValueConfigurator(){
        try {
            File propertyFile = new File(this.getClass().getClassLoader().getResource(PROPERTIES_FILE_NAME).getFile());
            InputStream propertyFileStream = new FileInputStream(propertyFile);
            Scanner sc = new Scanner(propertyFileStream).useDelimiter("\n");
            while (sc.hasNext()) {
                String[] StringArr = sc.next().split("=");
                propertyValueMap.put(StringArr[0], StringArr[1]);
            }
            System.out.println(propertyValueMap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    @SneakyThrows
    public void configure(Object t, AppContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Value.class)) {
                field.setAccessible(true);
                String propertyName = field.getAnnotation(Value.class).property();
                String value = propertyValueMap.get(propertyName);
                if (value == null) {
                    throw new RuntimeException( "Value for property" + propertyName + "was not found");
                }
                field.set(t, value);
            }
        }
    }
}
