package com.example.sms_1.view.fragments;

import org.junit.Test;
import java.lang.reflect.Method;
import static org.junit.Assert.*;


public class DataEntryFragmentTest {
    DataEntryFragment fragment = new DataEntryFragment();
    private boolean callValidateId(String id) throws Exception {
        Method method = DataEntryFragment.class.getDeclaredMethod("validateId", String.class);
        method.setAccessible(true);
        return (boolean) method.invoke(fragment, id);
    }

    private boolean callValidateAndSave(String name, String id, String email) throws Exception {
        Method method2 = DataEntryFragment.class.getDeclaredMethod("validateAndSave", String.class, String.class, String.class);
        method2.setAccessible(true);
        return (boolean) method2.invoke(fragment, name, id, email);
    }

    @Test
    public void testValidId() throws Exception {
        assertTrue(callValidateId("STU123"));
        assertFalse(callValidateId("STUABC999"));
    }

    @Test
    public void testInvalidId() throws Exception {
        assertFalse(callValidateId("12345"));
        assertFalse(callValidateId("STU12"));
        assertFalse(callValidateId("STUABC"));
        assertFalse(callValidateId(""));
    }

    @Test
    public void testValidEmail() {
        assertTrue("john@gmail.com".matches(fragment.EMAIL_PATTERN));
        assertTrue("abc.xyz123@gmail.com".matches(fragment.EMAIL_PATTERN));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse("john@yahoo.com".matches(fragment.EMAIL_PATTERN));
        assertFalse("john@gmail".matches(fragment.EMAIL_PATTERN));
        assertFalse("@gmail.com".matches(fragment.EMAIL_PATTERN));
        assertFalse("".matches(fragment.EMAIL_PATTERN));
    }
}
