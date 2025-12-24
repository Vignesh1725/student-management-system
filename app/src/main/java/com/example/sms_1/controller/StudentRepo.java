package com.example.sms_1.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sms_1.model.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    private static StudentRepo instance;
    private List<Student> studentList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();
    private static final String PREF_NAME = "students_db";
    private static final String KEY_LIST = "student_list";

    private StudentRepo(Context context) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        loadData();
    }
    public static StudentRepo getInstance(Context context) {
        if (instance == null) instance = new StudentRepo(context);
        return instance;
    }

    public List<Student> getStudents() {
        return studentList;
    }

    public boolean isDuplicate(String id, String email) {
        id = id.trim();
        email = email.trim();
        for (Student s : studentList) {
            if (s.getId().equals(id) || s.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    public void addStudent(Student s) {
        studentList.add(s);
        saveData();
    }

    public void deleteStudent(String id) {
        studentList.removeIf(s -> s.getId().equals(id));
        saveData();
    }

    public void updateStudent(String oldId, Student updatedStudent) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(oldId)) {
                studentList.set(i, updatedStudent);
                break;
            }
        }
        saveData();
    }
    public void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(studentList);
        editor.putString(KEY_LIST, json);
        editor.apply();
    }
    public void loadData() {
        String json = sharedPreferences.getString(KEY_LIST, null);
        Type type = new TypeToken<ArrayList<Student>>() {}.getType();
        studentList = gson.fromJson(json, type);

        if (studentList == null) {
            studentList = new ArrayList<>();
        }
    }

    public void clearData() {
        studentList.clear();
    }
}