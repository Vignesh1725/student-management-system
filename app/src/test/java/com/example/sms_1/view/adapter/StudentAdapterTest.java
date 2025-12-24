package com.example.sms_1.view.adapter;

import static org.junit.Assert.*;
import com.example.sms_1.controller.StudentRepo;
import com.example.sms_1.model.Student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class StudentAdapterTest {
    @Mock
    List<Student> mockStudentList;
    @Mock
    StudentAdapter.OnItemClickListener mockListener;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkStudentAdapter() {
        StudentAdapter studentAdapter = new StudentAdapter(mockStudentList, mockListener);
        assertNotNull(studentAdapter);
    }
}