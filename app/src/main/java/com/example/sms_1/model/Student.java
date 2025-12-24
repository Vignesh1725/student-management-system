package com.example.sms_1.model;

public class Student {
    private String name, id, email;
    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }
    public String getName() { return name; }
    public String getId() { return id; }
    public String getEmail() { return email; }
}
