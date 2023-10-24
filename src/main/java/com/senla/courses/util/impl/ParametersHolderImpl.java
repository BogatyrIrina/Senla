package com.senla.courses.util.impl;

import com.senla.courses.util.ParametersHolder;
import org.springframework.beans.factory.annotation.Value;

public class ParametersHolderImpl implements ParametersHolder {
    @Value("property1")
    private String text;
    @Override
    public String getSomeText() {
        return text;
    }
}
