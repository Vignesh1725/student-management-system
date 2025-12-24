package com.example.sms_1.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void checkStudentModel() {
        Student student1 = new Student("Vignesh", "STU001", "vicky@gmail.com");
        Student student2 = new Student("Kumaran", "STU002", "kumaran@gmail.com");
        assertNotEquals(student1, student2);
    }

    @Test
    public void checkGetId() {
        String studentId = "STU001";
        Student student3 = new Student("Vicky", "STU001", "vicky@gmail.com");
        assertEquals(studentId, student3.getId());
    }

    @Test
    public void checkGetName() {
        String studentName = "vicky";
        Student student3 = new Student("Vicky", "STU001", "vicky@gmail.com");
        assertNotEquals(studentName, student3.getName());
    }

    @Test
    public void checkGetEmail() {
        String studentEmail = "vicky@gmail.com";
        Student student3 = new Student("Vicky", "STU001", "vicky@gmail.com");
        assertEquals(studentEmail, student3.getEmail());
    }
}