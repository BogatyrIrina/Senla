package com.senla.courses.util.impl;

import com.senla.courses.annotation.Value;
import com.senla.courses.util.ParametersHolder;

public class ParametersHolderImpl implements ParametersHolder {
    @Value(property = "property1")
    private String text;
    @Override
    public String getSomeText() {
        return text;
    }
}
