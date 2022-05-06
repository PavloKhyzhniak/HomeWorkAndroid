package com.example.homeworkandroid.homework009;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework009.activity.Exercises001;

public class MainActivityHW009 extends AppCompatActivity {

    private Button btnGotoExercises001, btnReturnToMain;
    private TextView txvExercises001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework009_activity_main_hw009);

        findViews();

        setListeners();

        setAnimations();
    }

    private void setAnimations() {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);

        txvExercises001.setAnimation(myAnim);
        txvExercises001.startAnimation(myAnim);
    }

    private void setListeners() {
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercisesClick);

        btnReturnToMain.setOnClickListener(v -> returnToMain());

        // связь с обработчиком собыия клика по кнопке
        txvExercises001.setOnClickListener(this::gotoExercisesClick);
    }

    private void findViews() {
        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        txvExercises001 = findViewById(R.id.txvExercises001);
    }

    private void gotoExercisesClick(View view) {
        switch (view.getId()) {
            case R.id.btnGoToExercises001:
            case R.id.txvExercises001:
                Intent myIntent = new Intent(this, Exercises001.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
        }
    }

    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(myIntent);
        finish();
    }

    // region Работа с главным меню активности
    // обработчик события создани меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // связать разметку с ссылкой на меню
        // getMenuInflater() - загрузчик меню
        // inflate()         - загрузка меню
        getMenuInflater().inflate(R.menu.main_menu_hw009, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu

    // обработчик события выбора в меню
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniExercises001:
                Intent myIntent = new Intent(this, Exercises001.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.mniBack:
            case R.id.mniReturn:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.mniExit:
                finish();
                break;
        } // switch
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected
}