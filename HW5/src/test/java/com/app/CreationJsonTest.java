package com.app;

import junit.framework.TestCase;

import java.util.HashMap;

public class CreationJsonTest extends TestCase {

    public void testForWeightAbsence() {
        HashMap<String, String> description = new HashMap<>() {{
            put("firstName", "Ivan");
            put("lastName", "Ivanov");
            put("age", "19");
            put("weight", null);
        }};
        Person person = new Person(description);

        var json = CreationJson.convertToJson(person);

        assertFalse(json.contains("weight"));
    }

    public void testForWithoutNotIgnoreAnnotationAbsence() {
        HashMap<String, String> description = new HashMap<>() {{
            put("firstName", null);
            put("lastName", "Ivanov");
            put("age", "19");
            put("weight", null);
        }};
        Person person = new Person(description);

        var json = CreationJson.convertToJson(person);

        assertTrue(json.contains("\"firstName\" : \"null\""));
    }

    public void testForTheDefaultValueOfTheLastName() {
        HashMap<String, String> description = new HashMap<>() {{
            put("firstName", "Ivan");
            put("lastName", null);
            put("age", "19");
            put("weight", "80");
        }};
        Person person = new Person(description);

        var json = CreationJson.convertToJson(person);

        assertTrue(json.contains("\"name\" : \"\""));
    }
}