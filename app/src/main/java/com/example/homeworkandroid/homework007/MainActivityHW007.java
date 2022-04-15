package com.example.homeworkandroid.homework007;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.activity.AboutActivity;
import com.example.homeworkandroid.homework007.activity.Exercises001;
import com.example.homeworkandroid.homework007.activity.Exercises002;
import com.example.homeworkandroid.homework007.activity.Exercises003;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivityHW007 extends AppCompatActivity {

    private Button btnGotoExercises001, btnGotoExercises002, btnGotoExercises003, btnReturnToMain;
    private TextView txvExercises001, txvExercises002, txvExercises003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework007_activity_main_hw007);

        findViews();

        setListeners();

        setAnimations();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle(R.string.homework007_app_name);
    }

    private void setAnimations() {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);

        txvExercises001.setAnimation(myAnim);
        txvExercises001.startAnimation(myAnim);
        txvExercises002.setAnimation(myAnim);
        txvExercises002.startAnimation(myAnim);
        txvExercises003.setAnimation(myAnim);
        txvExercises003.startAnimation(myAnim);
    }

    private void setListeners() {
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercisesClick);
        btnGotoExercises002.setOnClickListener(this::gotoExercisesClick);
        btnGotoExercises003.setOnClickListener(this::gotoExercisesClick);

        btnReturnToMain.setOnClickListener(v -> returnToMain());

        // связь с обработчиком собыия клика по кнопке
        txvExercises001.setOnClickListener(this::gotoExercisesClick);
        txvExercises002.setOnClickListener(this::gotoExercisesClick);
        txvExercises003.setOnClickListener(this::gotoExercisesClick);
    }

    private void findViews() {
        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnGotoExercises002 = findViewById(R.id.btnGoToExercises002);
        btnGotoExercises003 = findViewById(R.id.btnGoToExercises003);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        txvExercises001 = findViewById(R.id.txvExercises001);
        txvExercises002 = findViewById(R.id.txvExercises002);
        txvExercises003 = findViewById(R.id.txvExercises003);
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
            case R.id.btnGoToExercises002:
            case R.id.txvExercises002:
//                myIntent = new Intent(this, Exercises002.class);
//                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(myIntent);
                break;
            case R.id.btnGoToExercises003:
            case R.id.txvExercises003:
                myIntent = new Intent(this, Exercises003.class);
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
        getMenuInflater().inflate(R.menu.main_menu_hw007, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu

    // обработчик события выбора в меню
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniData:
                new DatePickerDialog(
                        this,                  // контекст создания окна
                        dateSetListener,                    // слушатель события - дата изменена
                        dateAndTime.get(Calendar.YEAR),     // задать год, месяц, и день из объекта-календаря
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();  // показать диалог
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                item.setTitle(currentDate);
                break;
            case R.id.mniTime:
                new TimePickerDialog(
                        this,                      // контекст создания диалогового окна
                        timeSetListener,                        // слушатель события изменение времени в диалоге
                        dateAndTime.get(Calendar.HOUR_OF_DAY),  // час
                        dateAndTime.get(Calendar.MINUTE),       // минута
                        true)                                   // 24-х часовый формат времени
                        .show();
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                item.setTitle(currentTime);
                break;
            case R.id.mniJSONaviasales:
                Intent myIntent = new Intent(this, Exercises001.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.mniJSONhospital:
                break;
            case R.id.mniAnimation:
                myIntent = new Intent(this, Exercises003.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                break;
            case R.id.mniAbout:
                myIntent = new Intent(this, AboutActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
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

    // объект для работы с датой и временем
    Calendar dateAndTime = Calendar.getInstance();

    // установка обработчика изменения/выбора времени
    TimePickerDialog.OnTimeSetListener timeSetListener = (TimePicker view, int hourOfDay, int minute)
            -> {
        // подготовить и вывести новое время в строке отображения
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateAndTime.set(Calendar.MINUTE, minute);
    };

    // установка обработчика изменения/выбора даты
    private DatePickerDialog.OnDateSetListener dateSetListener =
            (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
                // подготовить и вывести новую дату в строке отображения
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            };
}