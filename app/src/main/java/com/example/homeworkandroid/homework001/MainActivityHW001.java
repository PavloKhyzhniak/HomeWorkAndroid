package com.example.homeworkandroid.homework001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homeworkandroid.R;

public class MainActivityHW001 extends AppCompatActivity {

    private Button btnGotoExercises001, btnGotoExercises002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hw01);

        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnGotoExercises002 = findViewById(R.id.btnGoToExercises002);

        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercises001Click);
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises002.setOnClickListener(this::gotoExercises002Click);

    }
    private void gotoExercises001Click(View view) {
        Intent myIntent = new Intent(this, Exercises001.class);
        startActivity(myIntent);
    }
    private void gotoExercises002Click(View view) {
        Intent myIntent = new Intent(this, Exercises002.class);
        startActivity(myIntent);
    }


}