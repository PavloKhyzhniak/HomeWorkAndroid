package com.example.homeworkandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.homework001.MainActivityHW001;
import com.example.homeworkandroid.homework002.MainActivityHW002;
import com.example.homeworkandroid.homework003.MainActivityHW003;
import com.example.homeworkandroid.homework004.LoginPage;
import com.example.homeworkandroid.homework004.MainActivityHW004;
import com.example.homeworkandroid.homework005.MainActivityHW005;

public class MainActivity extends AppCompatActivity {

    private Button btnGotoHW001, btnGotoHW002, btnGotoHW003, btnGotoHW004, btnGotoHW005
            , btnGotoHW006
            , btnExit;
    private ImageButton ibtnGotoLoginHW004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExit = findViewById(R.id.btnExit);
        btnGotoHW001 = findViewById(R.id.btnGoToHW001);
        btnGotoHW002 = findViewById(R.id.btnGoToHW002);
        btnGotoHW003 = findViewById(R.id.btnGoToHW003);
        btnGotoHW004 = findViewById(R.id.btnGoToHW004);
        btnGotoHW005 = findViewById(R.id.btnGoToHW005);
        btnGotoHW006 = findViewById(R.id.btnGoToHW006);
        ibtnGotoLoginHW004 = findViewById(R.id.btnGoToLoginHW004);

        // связь с обработчиком собыия клика по кнопке
        btnExit.setOnClickListener(this::onButtonClick);
        btnGotoHW001.setOnClickListener(this::onButtonClick);
        btnGotoHW002.setOnClickListener(this::onButtonClick);
        btnGotoHW003.setOnClickListener(this::onButtonClick);
        btnGotoHW004.setOnClickListener(this::onButtonClick);
        btnGotoHW005.setOnClickListener(this::onButtonClick);
        btnGotoHW006.setOnClickListener(this::onButtonClick);
        ibtnGotoLoginHW004.setOnClickListener(this::onButtonClick);

    }
    @SuppressLint("NonConstantResourceId")
    private void onButtonClick(View view) {
        Intent myIntent;
        switch(view.getId())
        {
            case R.id.btnGoToHW001:
                myIntent = new Intent(this, MainActivityHW001.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.btnGoToHW002:
                myIntent = new Intent(this, MainActivityHW002.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.btnGoToHW003:
                myIntent = new Intent(this, MainActivityHW003.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.btnGoToHW004:
                myIntent = new Intent(this, MainActivityHW004.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.btnGoToLoginHW004:
                myIntent = new Intent(this, LoginPage.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.btnGoToHW005:
                myIntent = new Intent(this, MainActivityHW005.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.btnGoToHW006:
//                myIntent = new Intent(this, MainActivityHW005.class);
//                startActivity(myIntent);
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }

}