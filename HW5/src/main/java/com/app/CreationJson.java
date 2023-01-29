package com.app;

import com.app.annotations.IgnoreNull;
import com.app.annotations.JsonElement;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CreationJson {

    public static String convertToJson(Object object) {
        try {
            return getJsonString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                if (!(field.isAnnotationPresent(IgnoreNull.class) && field.get(object) == null)) {
                    String annotationNameField = field.getAnnotation(JsonElement.class).name();
                    if (annotationNameField.equals("name") && field.get(object) == null) {
                        jsonElementsMap.put(annotationNameField, "");
                    } else if (!annotationNameField.equals("")) {
                        jsonElementsMap.put(annotationNameField, (String) field.get(object));
                    } else {
                        jsonElementsMap.put(field.getName(), (String) field.get(object));
                    }
                }
            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> {
                    try {
                        var num = Integer.parseInt(entry.getValue());
                        return "\"" + entry.getKey() + "\" : "
                               + num;
                    } catch (Exception ex) {
                        return "\"" + entry.getKey() + "\" : \""
                               + entry.getValue() + "\"";
                    }
                })
                .collect(Collectors.joining(",\n"));
        return "{\n" + jsonString + "\n}";
    }
}
