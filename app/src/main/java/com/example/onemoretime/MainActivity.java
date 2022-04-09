package com.example.onemoretime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.onemoretime.data.repo.CompetitionsRepo;
import com.example.onemoretime.dialogs.DialogListener;

public class MainActivity extends AppCompatActivity implements DialogListener {

    private final String TAG = MainActivity.class.getSimpleName();

    private CompetitionsRepo competitionsRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

