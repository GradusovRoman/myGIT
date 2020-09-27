package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParamBuilder {
    private HashMap<String, Object> parameters;

    public ParamBuilder(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public ParamBuilder add(String name, Object value) {
        this.parameters.put(name, new Object[]{value});
        return this;
    }


    public ParamBuilder remove(String... names) {
        Stream.of(names).forEach(name -> this.parameters.remove(name));
        return this;
    }

    public String compile() {
        return (this.parameters.isEmpty()) ? "" : "?" + this.parameters.entrySet().stream()
                .map(stringObjectEntry ->
                        String.format("%s=%s", stringObjectEntry.getKey(), ((Object[]) stringObjectEntry.getValue())[0]))
                .reduce((s, s2) -> s + "&" + s2)
                .get();
    }

    public List<Map.Entry<String, String>> getValuesList() {
        return new ArrayList<>(this.parameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> ((String[]) entry.getValue())[0]))
                .entrySet());
    }
}
