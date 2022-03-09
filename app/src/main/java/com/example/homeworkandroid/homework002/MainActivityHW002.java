package com.example.homeworkandroid.homework002;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.R;

public class MainActivityHW002 extends AppCompatActivity {

    private Button btnGotoExercises001, btnGotoExercises002;
    private TextView txvExercises001,txvExercises002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hw02);

        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnGotoExercises002 = findViewById(R.id.btnGoToExercises002);
        txvExercises001 = findViewById(R.id.txvExercises001);
        txvExercises002 = findViewById(R.id.txvExercises002);

        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercises001Click);
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises002.setOnClickListener(this::gotoExercises002Click);

        // связь с обработчиком собыия клика по кнопке
        txvExercises001.setOnClickListener(this::gotoExercises001Click);
        // связь с обработчиком собыия клика по кнопке
        txvExercises002.setOnClickListener(this::gotoExercises002Click);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);

        txvExercises001.setAnimation(myAnim);
        txvExercises002.setAnimation(myAnim);
txvExercises001.startAnimation(myAnim);
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