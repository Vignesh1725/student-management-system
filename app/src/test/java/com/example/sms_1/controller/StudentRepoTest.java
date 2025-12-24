package com.example.sms_1.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import android.content.Context;
import android.content.SharedPreferences;

import org.mockito.DoNotMock;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import com.example.sms_1.model.Student;
import com.google.gson.Gson;

import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

public class StudentRepoTest {
    @Mock Context mockContext;
    @Mock
    SharedPreferences mockSharedPreferences;
    @Mock
    SharedPreferences.Editor mockEditor;

    private StudentRepo studentRepo;
    private static final String KEY_LIST = "student_list";

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getApplicationContext()).thenReturn(mockContext);
        when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        studentRepo = StudentRepo.getInstance(mockContext);
        studentRepo.clearData();
    }

    @Test
    public void checkIsDuplicate() {
        boolean actualResult = studentRepo.isDuplicate("STU001", "vicky@gmail.com");
        assertFalse(actualResult);
    }

    @Test
    public void checkAddStudent() {
        Student student = new Student("Vignesh", "STU001", "vicky@gmail.com");
        studentRepo.addStudent(student);
        assertTrue(studentRepo.getStudents().contains(student));
    }

    @Test
    public void checkDeleteStudent() {
        Student student = new Student("Vignesh", "STU001", "vicky@gmail.com");
        assertEquals(0, studentRepo.getStudents().size());
        studentRepo.addStudent(student);
        assertEquals(1, studentRepo.getStudents().size());
    }

    @Test
    public void checkUpdateStudent1() {
        Student student = new Student("Vignesh", "STU001", "vicky@gmail.com");
        studentRepo.addStudent(student);
        assertEquals(1, studentRepo.getStudents().size());
        Student updatedStudent = new Student("Vigneshkumaran", "STU055", "vigneshkumaran@gmail.com");
        studentRepo.updateStudent("STU001", updatedStudent);
        assertEquals(1, studentRepo.getStudents().size());
    }

    @Test
    public void checkAddStudent2() {
        Student student1 = new Student("Vignesh", "STU001", "vicky@gmail.com");
        Student student2 = new Student("Kumaran", "STU002", "kum@gmail.com");
        studentRepo.addStudent(student1);
        studentRepo.addStudent(student2);
        assertEquals(student2, studentRepo.getStudents().get(studentRepo.getStudents().size()-1));
    }

    @Test
    public void checkUpdateStudent2() {
        Student student = new Student("Vignesh", "STU001", "vicky@gmail.com");
        Student updatedStudent = new Student("Kumaran", "STU002", "kum@gmail.com");
        studentRepo.addStudent(student);
        studentRepo.updateStudent( "STU001",updatedStudent);
        assertNotEquals(student, studentRepo.getStudents().get(studentRepo.getStudents().size()-1));
    }

    @After
    public void tearDown() {
        studentRepo.clearData();
    }
}