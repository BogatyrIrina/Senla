package com.senla.courses.util.impl;

import com.senla.courses.annotation.Component;
import com.senla.courses.util.ParametersHolder;

@Component
public class MyParametersHolderSuperClassImpl implements ParametersHolder {
    @Override
    public String getSomeText() {
        return "Мой очень крутой текст";
    }
}
