package com.example.homeworkandroid.homework001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homeworkandroid.R;

import java.util.Locale;

public class Exercises001 extends AppCompatActivity {

    private TextView txvArea;
    private TextView txvPerimeter;
    private Button btnArea, btnPerimeter, btnGenerate, btnGotoExercises002,btnReturnToMain;
    private EditText edtSideA, edtSideB,edtSideC;
    private static Triangle triangle = new Triangle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises001);

        txvArea = findViewById(R.id.area);
        txvPerimeter = findViewById(R.id.perimetr);
        btnArea = findViewById(R.id.btnArea);
        btnPerimeter = findViewById(R.id.btnPerimetr);
        btnGenerate = findViewById(R.id.btnGenerateSideValues);
        btnGotoExercises002 = findViewById(R.id.btnGoToExercises002);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);
        edtSideA = findViewById(R.id.sideA);
        edtSideB = findViewById(R.id.sideB);
        edtSideC = findViewById(R.id.sideC);

        // связь с обработчиком собыия клика по кнопке
        btnReturnToMain.setOnClickListener(this::returnToMain);

        // связь с обработчиком собыия клика по кнопке
        btnArea.setOnClickListener(this::areaClick);

        // связь с обработчиком собыия клика по кнопке
        btnPerimeter.setOnClickListener(this::perimeterClick);

        // связь с обработчиком собыия клика по кнопке
        btnGenerate.setOnClickListener(this::generateClick);

        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises002.setOnClickListener(this::gotoExercises002Click);

        //edtSideA.setOnFocusChangeListener(this::onFocusChange);
        //edtSideB.setOnFocusChangeListener(this::onFocusChange);
        //edtSideC.setOnFocusChangeListener(this::onFocusChange);
         class GenericTextWatcher implements TextWatcher{

            private View view;
            private GenericTextWatcher(View view) {
                this.view = view;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                switch(view.getId()){
                    case R.id.sideA:
                        Exercises001.triangle.setSideA(Double.parseDouble(text));
                        break;
                    case R.id.sideB:
                        Exercises001.triangle.setSideB(Double.parseDouble(text));
                        break;
                    case R.id.sideC:
                        Exercises001.triangle.setSideC(Double.parseDouble(text));
                        break;
                }
            }
        }

        edtSideA.addTextChangedListener(new GenericTextWatcher(edtSideA));
        edtSideB.addTextChangedListener(new GenericTextWatcher(edtSideB));
        edtSideC.addTextChangedListener(new GenericTextWatcher(edtSideC));
    }

//    public void onFocusChange(View v, boolean hasFocus) {
//        switch(v.getId()){
//            case R.id.sideA:
//                this.triangle.setSideA(Double.parseDouble(edtSideA.getText().toString()));
//                break;
//            case R.id.sideB:
//                this.triangle.setSideB(Double.parseDouble(edtSideB.getText().toString()));
//                break;
//            case R.id.sideC:
//                this.triangle.setSideC(Double.parseDouble(edtSideC.getText().toString()));
//                break;
//        }
//    }

    private void returnToMain(View view) {
        Intent myIntent = new Intent(this, MainActivityHW001.class);
        startActivity(myIntent);
    }
    private void gotoExercises002Click(View view) {
        Intent myIntent = new Intent(this, Exercises002.class);
        startActivity(myIntent);
    }

    private void areaClick(View view) {
        txvArea.setText(String.format("%.3f", this.triangle.CalculateArea()));
    }

    private void perimeterClick(View view) {
        txvPerimeter.setText(String.format("%.3f", this.triangle.CalculatePerimeter()));
    }

    private void generateClick(View view) {
        this.triangle.Generate(1.,100.);
        edtSideA.setText(String.format(Locale.ROOT,"%.3f", this.triangle.getSideA()));
        edtSideB.setText(String.format(Locale.ROOT,"%.3f", this.triangle.getSideB()));
        edtSideC.setText(String.format(Locale.ROOT,"%.3f", this.triangle.getSideC()));
    }
}