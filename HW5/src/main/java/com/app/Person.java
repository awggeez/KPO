package com.app;

import com.app.annotations.IgnoreNull;
import com.app.annotations.JsonElement;
import com.app.annotations.JsonSerializable;

import java.util.HashMap;

@JsonSerializable
public class Person {
    @JsonElement
    private String firstName;

    @JsonElement(name = "name")
    private String lastName;

    @JsonElement
    private String age;

    @IgnoreNull
    @JsonElement
    private String weight;

    Person(HashMap<String, String> personDescription) {
        this.firstName = personDescription.get("firstName");
        this.lastName = personDescription.get("lastName");
        this.age = personDescription.get("age");
        this.weight = personDescription.get("weight");
    }
}