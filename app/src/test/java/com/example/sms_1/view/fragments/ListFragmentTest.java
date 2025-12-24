package com.example.sms_1.view.fragments;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sms_1.R;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ListFragmentTest {
    @Mock
    LayoutInflater mockInflator;
    @Mock
    ViewGroup mockContainer;
    @Mock
    Bundle mockSavedInstanceState;
    @Mock
    View mockView;
    @Mock
    ImageView mockImageView;
    @Mock
    Button mockBtn;
    @Mock
    RecyclerView mockRV;

    @Test
    public void checkOnCreateView() {
        MockitoAnnotations.openMocks(this);
        when(mockView.findViewById(R.id.btnBack)).thenReturn(mockImageView);
        when(mockView.findViewById(R.id.recyclerView)).thenReturn(mockRV);
        when(mockView.findViewById(R.id.fabAdd)).thenReturn(mockBtn);
    }
}