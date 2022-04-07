package com.example.homeworkandroid.homework001.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework001.MainActivityHW001;
import com.example.homeworkandroid.homework001.models.BaseProduct;

public class Exercises002 extends AppCompatActivity {

    private Button btnSummary, btnGotoExercises001,btnReturnToMain;
    private TextView txvSummary;
    private EditText edtName, edtPrice,edtCount;
    private BaseProduct baseProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework001_activity_exercises002);

        txvSummary = findViewById(R.id.txvSummary);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtCount = findViewById(R.id.edtCount);

        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);
        btnSummary = findViewById(R.id.btnSummary);

        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercises001Click);

        // связь с обработчиком собыия клика по кнопке
        btnReturnToMain.setOnClickListener(this::returnToMain);

        // связь с обработчиком собыия клика по кнопке
        btnSummary.setOnClickListener(this::summaryClick);

    }

    @SuppressLint("SetTextI18n")
    private void summaryClick(View view) {
        this.baseProduct = new BaseProduct();
        this.baseProduct.setName(edtName.getText().toString());
        this.baseProduct.setCount(Integer.parseInt(edtCount.getText().toString()));
        this.baseProduct.setPrice(Integer.parseInt(edtPrice.getText().toString()));

        txvSummary.setText(this.baseProduct.Summary().toString());
    }

    private void returnToMain(View view) {
        Intent myIntent = new Intent(this, MainActivityHW001.class);
        startActivity(myIntent);
    }

    private void gotoExercises001Click(View view) {
        Intent myIntent = new Intent(this, Exercises001.class);
        startActivity(myIntent);
    }
}