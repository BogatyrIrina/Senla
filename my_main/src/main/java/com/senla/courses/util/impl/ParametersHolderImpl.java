package com.senla.courses.util.impl;

import com.senla.courses.util.ParametersHolder;

public class ParametersHolderImpl implements ParametersHolder {
    private static final String SOMETEXT = "Это тестовый текст для проверки архитектуры приложения";
    @Override
    public String getSomeText() {
        return SOMETEXT;
    }
}
