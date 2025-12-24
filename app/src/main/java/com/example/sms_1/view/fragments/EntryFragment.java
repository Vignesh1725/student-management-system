package com.example.sms_1.view.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.sms_1.R;

public class EntryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        btnAction(view);
        return view;
    }

    public void btnAction(View view) {
        Button btn = view.findViewById(R.id.btnEnter);
        btn.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ListFragment())
                    .addToBackStack(null).commit();
        });
    }
}
