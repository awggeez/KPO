package com.app;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> personDescription = new HashMap<>();

        personDescription.put("firstName", null);
        personDescription.put("lastName", "null");
        personDescription.put("age", "19");
        personDescription.put("weight", "72");

        Person person = new Person(personDescription);
        var json = CreationJson.convertToJson(person);
        System.out.println(json);
    }
}
