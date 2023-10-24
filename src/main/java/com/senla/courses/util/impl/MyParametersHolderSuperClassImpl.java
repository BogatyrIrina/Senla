package com.senla.courses.util.impl;

import com.senla.courses.util.ParametersHolder;
import org.springframework.stereotype.Component;

@Component
public class MyParametersHolderSuperClassImpl implements ParametersHolder {
    @Override
    public String getSomeText() {
        return "Мой очень крутой текст";
    }
}
