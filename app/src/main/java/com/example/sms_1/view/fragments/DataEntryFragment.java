package com.example.sms_1.view.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.sms_1.R;
import com.example.sms_1.controller.StudentRepo;
import com.example.sms_1.model.Student;

public class DataEntryFragment extends Fragment {
    private EditText etName, etId, etEmail;
    private String existingId = null;
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_.-]+@gmail\\.com$";
    public static final String PATTERN_ID = "^STU\\d{3,}$";
    private static final DataEntryFragment fragment = new DataEntryFragment();
    private static final Bundle args = new Bundle();


    public static DataEntryFragment newInstance(String name, String id, String email) {
        args.putString("name", name);
        args.putString("id", id);
        args.putString("email", email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_entry, container, false);
        etName = view.findViewById(R.id.etName);
        etId = view.findViewById(R.id.etId);
        etEmail = view.findViewById(R.id.etEmail);
        Button btnSave = view.findViewById(R.id.btnSave);
        ImageView btnBack = view.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStack();
            }
        });

        if (getArguments() != null) {
            etName.setText(getArguments().getString("name"));
            etId.setText(getArguments().getString("id"));
            etEmail.setText(getArguments().getString("email"));
            existingId = getArguments().getString("id");
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String id = etId.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            validateAndSave(name, id, email);
        });
        return view;
    }

    private void validateAndSave(String name, String id, String email) {

        if (name.isEmpty() || id.isEmpty() || email.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validateId(id)) {
            etId.setError("Invalid Id");
            return;
        }

        if (!email.matches(EMAIL_PATTERN)) {
            etEmail.setError("Invalid Email");
            return;
        }
        else {
            if (existingId != null) {
                StudentRepo.getInstance(requireContext()).updateStudent(existingId, new Student(name, id, email));
                Toast.makeText(getContext(), "Student Updated", Toast.LENGTH_SHORT).show();
            } else {
                if (StudentRepo.getInstance(requireContext()).isDuplicate(id, email)) {
                    Toast.makeText(getContext(), "ID or Email already exists!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StudentRepo.getInstance(requireContext()).addStudent(new Student(name, id, email));
            }
            getParentFragmentManager().popBackStack();
        }
    }

    private boolean validateId(String id) {
        return id.matches(PATTERN_ID);
    }
}