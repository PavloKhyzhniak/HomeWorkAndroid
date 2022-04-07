package com.example.homeworkandroid.homework003;

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
import com.example.homeworkandroid.homework003.activity.AnimalAdapterActivity;
import com.example.homeworkandroid.homework003.activity.ShipAdapterActivity;

public class MainActivityHW003 extends AppCompatActivity {

    private Button btnGotoExercises001, btnGotoExercises002, btnReturnToMain;
    private TextView txvExercises001,txvExercises002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework003_activity_main_hw003);

        findViews();

        setListeners();

        setAnimations();
    }

    private void setAnimations() {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);

        txvExercises001.setAnimation(myAnim);
        txvExercises002.setAnimation(myAnim);
        txvExercises001.startAnimation(myAnim);
        txvExercises002.startAnimation(myAnim);
    }

    private void setListeners() {
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercises001Click);
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises002.setOnClickListener(this::gotoExercises002Click);

        btnReturnToMain.setOnClickListener(v -> returnToMain());


        // связь с обработчиком собыия клика по кнопке
        txvExercises001.setOnClickListener(this::gotoExercises001Click);
        // связь с обработчиком собыия клика по кнопке
        txvExercises002.setOnClickListener(this::gotoExercises002Click);
    }

    private void findViews() {
        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnGotoExercises002 = findViewById(R.id.btnGoToExercises002);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        txvExercises001 = findViewById(R.id.txvExercises001);
        txvExercises002 = findViewById(R.id.txvExercises002);
    }

    private void gotoExercises001Click(View view) {
        Intent myIntent = new Intent(this, AnimalAdapterActivity.class);
        startActivity(myIntent);
    }
    private void gotoExercises002Click(View view) {
        Intent myIntent = new Intent(this, ShipAdapterActivity.class);
        startActivity(myIntent);
    }
    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    // region Работа с главным меню активности
    // обработчик события создани меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // связать разметку с ссылкой на меню
        // getMenuInflater() - загрузчик меню
        // inflate()         - загрузка меню
        getMenuInflater().inflate(R.menu.main_menu_hw003, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu


    // обработчик события выбора в меню
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniAnimalAdapter:
                startActivity(new Intent(this, AnimalAdapterActivity.class));
                break;
            case R.id.mniShipAdapter:
                startActivity(new Intent(this, ShipAdapterActivity.class));
                break;
            case R.id.mniReturn:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.mniExit:
                finish();
                break;
        } // switch
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected

    // endregion
}