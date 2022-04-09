package com.example.onemoretime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.onemoretime.MatchSouting.MatchAutoFragment;
import com.example.onemoretime.MatchSouting.MatchTeleFragment;

public class MainActivity extends AppCompatActivity {

    //reference to all buttons and controls on layout
    Button btn_plusMatch, btn_minusMatch, btn_startMatch;
    Spinner spinner_teamNum, spinner_deviceNum;
    TextView tv_matchNum;
    private int currMatchNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_plusMatch = findViewById(R.id.btn_plusMatch);
        btn_minusMatch = findViewById(R.id.btn_minusMatch);
        btn_startMatch = findViewById(R.id.btn_startMatch);
        spinner_teamNum = findViewById(R.id.teamSpinner);
        spinner_deviceNum = findViewById(R.id.deviceSpinner);
        tv_matchNum = findViewById(R.id.tv_matchNumber);



        btn_plusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currMatchNum ++;
                tv_matchNum.setText(String.valueOf(currMatchNum));
            }
        });

        btn_minusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currMatchNum == 1){

                }
                else {
                    currMatchNum --;
                    tv_matchNum.setText(String.valueOf(currMatchNum));
                }

            }
        });

        btn_startMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MatchTeleFragment.class);
                startActivity(intent);

            }
        });


    }
}