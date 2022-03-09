package com.example.homeworkandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.homework001.Exercises001;
import com.example.homeworkandroid.homework001.Exercises002;
import com.example.homeworkandroid.homework001.MainActivityHW001;
import com.example.homeworkandroid.homework002.MainActivityHW002;

public class MainActivity extends AppCompatActivity {

    private Button btnGotoHW001, btnGotoHW002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGotoHW001 = findViewById(R.id.btnGoToHW001);
        btnGotoHW002 = findViewById(R.id.btnGoToHW002);

        // связь с обработчиком собыия клика по кнопке
        btnGotoHW001.setOnClickListener(this::gotoHW001Click);
        // связь с обработчиком собыия клика по кнопке
        btnGotoHW002.setOnClickListener(this::gotoHW002Click);

    }
    private void gotoHW001Click(View view) {
        Intent myIntent = new Intent(this, MainActivityHW001.class);
        startActivity(myIntent);
    }
    private void gotoHW002Click(View view) {
        Intent myIntent = new Intent(this, MainActivityHW002.class);
        startActivity(myIntent);
    }


}