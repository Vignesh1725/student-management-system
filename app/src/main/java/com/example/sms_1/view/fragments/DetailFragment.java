package com.example.sms_1.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sms_1.R;
import com.example.sms_1.controller.StudentRepo;

public class DetailFragment extends Fragment {

    private String name, id, email;
    public static DetailFragment newInstance(String name, String id, String email) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("id", id);
        args.putString("email", email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("name");
            id = getArguments().getString("id");
            email = getArguments().getString("email");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView btnBack = view.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStack();
            }
        });

        displayDetails(view);
        setupActionButtons(view);
        return view;
    }

    private void displayDetails(View view) {
        ((TextView) view.findViewById(R.id.tvDetailName)).setText(getArguments().getString("name"));
        ((TextView) view.findViewById(R.id.tvDetailId)).setText(getArguments().getString("id"));
        ((TextView) view.findViewById(R.id.tvDetailEmail)).setText(getArguments().getString("email"));
    }

    private void setupActionButtons(View view) {
        Button btnDelete = view.findViewById(R.id.tvDelete);
        Button btnUpdate = view.findViewById(R.id.tvUpdate);

        btnDelete.setOnClickListener(v -> handleDeleteAction());
        btnUpdate.setOnClickListener(v -> handleUpdateAction());
    }

    private void handleDeleteAction() {
        StudentRepo.getInstance(requireContext()).deleteStudent(id);
        Toast.makeText(getContext(), "Student deleted successfully", Toast.LENGTH_SHORT).show();
        getParentFragmentManager().popBackStack();
    }

    private void handleUpdateAction() {

        DataEntryFragment updateFragment = DataEntryFragment.newInstance(name, id, email);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, updateFragment)
                .addToBackStack(null)
                .commit();
    }
}