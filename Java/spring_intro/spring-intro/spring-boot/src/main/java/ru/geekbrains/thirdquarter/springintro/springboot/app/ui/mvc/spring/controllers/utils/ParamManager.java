package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers.utils;

import java.util.HashMap;
import java.util.Map;

public class ParamManager {
    private Map<String, Object> parameters = new HashMap<>();

    public ParamManager setAll(Map<String, Object> parameters) {
        this.parameters = new HashMap<>(parameters);
        return this;
    }

    public ParamBuilder getBuilder() {
        return new ParamBuilder(new HashMap<>(parameters));
    }
}
