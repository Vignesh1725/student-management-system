package com.example.sms_1.view.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sms_1.R;
import com.example.sms_1.controller.StudentRepo;
import com.example.sms_1.view.adapter.StudentAdapter;
import com.google.gson.Gson;

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ImageView btnBack = view.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStack();
            }
        });

        RecyclerView rv = view.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        StudentAdapter adapter = new StudentAdapter(StudentRepo.getInstance(requireContext()).getStudents(), student -> {
            DetailFragment detailFrag = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", student.getName());
            bundle.putString("id", student.getId());
            bundle.putString("email", student.getEmail());
            detailFrag.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFrag).addToBackStack(null).commit();
        });

        rv.setAdapter(adapter);

        view.findViewById(R.id.fabAdd).setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DataEntryFragment()).addToBackStack(null).commit();
        });

        return view;
    }
}