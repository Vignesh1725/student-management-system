package com.example.sms_1.view.fragments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.example.sms_1.R;

public class EntryFragmentTest {

    @Test
    public void setUp() {
        LayoutInflater mockInflater = mock(LayoutInflater.class);
        ViewGroup mockContainer = mock(ViewGroup.class);
        Bundle mockSavedInstanceState = mock(Bundle.class);

        View mockView = mock(View.class);
        Button mockButton = mock(Button.class);

        when(mockInflater.inflate(anyInt(), eq(mockContainer), anyBoolean())).thenReturn(mockView);
        when(mockView.findViewById(anyInt())).thenReturn(mockButton);

        EntryFragment fragment = new EntryFragment();
        View result = fragment.onCreateView(mockInflater, mockContainer, mockSavedInstanceState);
        assertNotNull(result);

        verify(mockView).findViewById(R.id.btnEnter);
        verify(mockButton).setOnClickListener(any());
    }

}
