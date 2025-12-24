package com.example.sms_1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sms_1.view.fragments.EntryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFragment(savedInstanceState);
    }

    protected void startFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new EntryFragment())
                    .commit();
        }
    }

}