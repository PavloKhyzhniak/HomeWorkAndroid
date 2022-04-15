package com.example.homeworkandroid.homework007.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.fragments.ComboFragment;
import com.example.homeworkandroid.homework007.fragments.RotateFragment;
import com.example.homeworkandroid.homework007.fragments.ScaleFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Exercises003 extends AppCompatActivity {

    private Button btnReturnToMain,
            btnScale, btnRotate, btnTransparent, btnVisibility, btnCombo;

    ImageView imgElementAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework007_activity_exercises003);

        findViews();

        setListeners();

    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnScale = findViewById(R.id.btnScale);
        btnRotate = findViewById(R.id.btnRotate);
        btnTransparent = findViewById(R.id.btnTransparent);
        btnVisibility = findViewById(R.id.btnVisibility);
        btnCombo = findViewById(R.id.btnCombo);

        imgElementAnimation = findViewById(R.id.imgElementAnimation);
    }

    private void setListeners() {
        btnReturnToMain.setOnClickListener(this::OnClickListener);

        btnScale.setOnClickListener(this::OnClickListener);
        btnRotate.setOnClickListener(this::OnClickListener);
        btnTransparent.setOnClickListener(this::OnClickListener);
        btnVisibility.setOnClickListener(this::OnClickListener);
        btnCombo.setOnClickListener(this::OnClickListener);
    }

    @SuppressLint("NonConstantResourceId")
    private void OnClickListener(View v) {
        //скроем все фрагменты параметров анимации
        FrameLayout frameLayout = findViewById(R.id.frlFragment);
        for (int index = 0; index < frameLayout.getChildCount(); index++) {
            View nextChild = frameLayout.getChildAt(index);
            nextChild.setVisibility(View.INVISIBLE);
        }

        switch (v.getId()) {
            case R.id.btnReturnToMain:
                Intent myIntent = new Intent(this, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(myIntent);
                finish();
                break;
            case R.id.btnScale: {
                FragmentContainerView fr = findViewById(R.id.frScale);
                fr.setVisibility(View.VISIBLE);

                imgElementAnimation.clearAnimation();

                // получаем ссылку на фрагмент-приемник
                ScaleFragment fragmentScale = (ScaleFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frScale);

                //получаем модель настроек выбранной анимации
                assert fragmentScale != null;
                com.example.homeworkandroid.homework007.models.ScaleAnimation model = fragmentScale.scaleViewModel.getModel();

                //подготовим анимацию с настройками из модели
                ScaleAnimation scale = new ScaleAnimation(model.getFromXScale() / 100f, model.getToXScale() / 100f,
                        model.getFromYScale() / 100f, model.getToYScale() / 100f,
                        Animation.RELATIVE_TO_SELF, model.getPivotX() / 100.0f, Animation.RELATIVE_TO_SELF, model.getPivotY() / 100.0f);
                scale.setDuration(model.getDuration());
                scale.setRepeatMode(model.isRepeatMode() ? 2 : 1);
                scale.setRepeatCount(model.getRepeatCount());
                scale.setInterpolator(new LinearInterpolator());

                imgElementAnimation.startAnimation(scale);
            }
            break;
            case R.id.btnRotate: {
                FragmentContainerView fr = findViewById(R.id.frRotate);
                fr.setVisibility(View.VISIBLE);

                imgElementAnimation.clearAnimation();

                // получаем ссылку на фрагмент-приемник
                RotateFragment fragmentRotate = (RotateFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frRotate);

                //получаем модель настроек выбранной анимации
                assert fragmentRotate != null;
                com.example.homeworkandroid.homework007.models.RotateAnimation model = fragmentRotate.rotateViewModel.getModel();

                //подготовим анимацию с настройками из модели
                RotateAnimation rotate = new RotateAnimation(model.getFromDegrees(), model.getToDegrees(), Animation.RELATIVE_TO_SELF, model.getPivotX() / 100.0f, Animation.RELATIVE_TO_SELF, model.getPivotY() / 100.0f);
                rotate.setDuration(model.getDuration());
                rotate.setRepeatMode(model.isRepeatMode() ? 2 : 1);
                rotate.setRepeatCount(model.getRepeatCount());
                rotate.setInterpolator(new LinearInterpolator());

                imgElementAnimation.startAnimation(rotate);
            }
            break;
            case R.id.btnTransparent:
                break;
            case R.id.btnVisibility:
                break;
            case R.id.btnCombo: {
                FragmentContainerView fr = findViewById(R.id.frCombo);
                fr.setVisibility(View.VISIBLE);

                imgElementAnimation.clearAnimation();

                // получаем ссылку на фрагмент-приемник
                ComboFragment fragmentCombo = (ComboFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frCombo);

                //получаем модель настроек выбранной анимации
                assert fragmentCombo != null;
                // не работает связь с моделью комбо!!!! берем прям из фрагментов
                com.example.homeworkandroid.homework007.models.ComboAnimation model = fragmentCombo.comboViewModel.getModel();

                com.example.homeworkandroid.homework007.models.RotateAnimation modelRotate =
                        ((RotateFragment) fragmentCombo.getChildFragmentManager().findFragmentById(R.id.frRotate)).rotateViewModel.getModel();

                //подготовим анимацию с настройками из модели
                RotateAnimation rotate = new RotateAnimation(modelRotate.getFromDegrees(), modelRotate.getToDegrees(), Animation.RELATIVE_TO_SELF, modelRotate.getPivotX() / 100.0f, Animation.RELATIVE_TO_SELF, modelRotate.getPivotY() / 100.0f);
                rotate.setDuration(modelRotate.getDuration());
                rotate.setRepeatMode(modelRotate.isRepeatMode() ? 2 : 1);
                rotate.setRepeatCount(modelRotate.getRepeatCount());
                rotate.setInterpolator(new LinearInterpolator());

                com.example.homeworkandroid.homework007.models.ScaleAnimation modelScale =
                        ((ScaleFragment) fragmentCombo.getChildFragmentManager().findFragmentById(R.id.frScale)).scaleViewModel.getModel();

                //подготовим анимацию с настройками из модели
                ScaleAnimation scale = new ScaleAnimation(modelScale.getFromXScale() / 100f, modelScale.getToXScale() / 100f,
                        modelScale.getFromYScale() / 100f, modelScale.getToYScale() / 100f,
                        Animation.RELATIVE_TO_SELF, modelScale.getPivotX() / 100.0f, Animation.RELATIVE_TO_SELF, modelScale.getPivotY() / 100.0f);
                scale.setDuration(modelScale.getDuration());
                scale.setRepeatMode(modelScale.isRepeatMode() ? 2 : 1);
                scale.setRepeatCount(modelScale.getRepeatCount());
                scale.setInterpolator(new LinearInterpolator());

                AnimationSet animSet = new AnimationSet(true);
                animSet.addAnimation(scale);
                animSet.addAnimation(rotate);

                imgElementAnimation.startAnimation(animSet);
            }
            break;
        }
    }



    // region Работа с главным меню активности
    // обработчик события создани меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // связать разметку с ссылкой на меню
        // getMenuInflater() - загрузчик меню
        // inflate()         - загрузка меню
        getMenuInflater().inflate(R.menu.back_menu, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu


    // обработчик события выбора в меню
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniBack:
                finish();
                break;
        } // switch
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected

    // endregion
}