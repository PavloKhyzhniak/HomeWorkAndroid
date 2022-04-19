package com.example.homeworkandroid.homework007.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.adapters.ViewAnimationAdapter;
import com.example.homeworkandroid.homework007.fragments.ComboFragment;
import com.example.homeworkandroid.homework007.fragments.RotateFragment;
import com.example.homeworkandroid.homework007.fragments.ScaleFragment;
import com.example.homeworkandroid.homework007.fragments.TranslateFragment;
import com.example.homeworkandroid.homework007.models.BaseAnimationModel;
import com.example.homeworkandroid.homework007.models.ComboAnimationModel;
import com.example.homeworkandroid.homework007.models.RotateAnimationModel;
import com.example.homeworkandroid.homework007.models.ScaleAnimationModel;
import com.example.homeworkandroid.homework007.models.TranslateAnimationModel;
import com.example.homeworkandroid.homework007.models.ViewAnimationModel;
import com.example.homeworkandroid.homework007.viewmodel.ScaleViewModel;
import com.example.homeworkandroid.homework007.viewmodel.ViewAnimationViewModel;

public class Exercises003 extends AppCompatActivity {

    private Button btnReturnToMain,
            btnScale, btnRotate, btnTransparent, btnVisibility, btnCombo;

    FrameLayout frlFragment;

    ImageView imgElementAnimation;

    ViewAnimationViewModel viewAnimationViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewAnimationViewModel = new ViewModelProvider(this).get(ViewAnimationViewModel.class);

        com.example.homeworkandroid.databinding.Homework007ActivityExercises003Binding binding = DataBindingUtil.setContentView(this, R.layout.homework007_activity_exercises003);

        ViewAnimationModel viewAnimationModel = new ViewAnimationModel();
        binding.setVm(viewAnimationModel);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewAnimationViewModel.setModel(viewAnimationModel);
            }
        }, 1000);

        setContentView(binding.getRoot());

        findViews();

        setListeners();
    }

    private void findViews() {
        // получить ссылки на элементы интерфейса
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        btnScale = findViewById(R.id.btnScale);
        btnRotate = findViewById(R.id.btnRotate);
        btnTransparent = findViewById(R.id.btnTranslate);
        btnVisibility = findViewById(R.id.btnVisibility);
        btnCombo = findViewById(R.id.btnCombo);

        frlFragment = findViewById(R.id.frlFragment);

        imgElementAnimation = findViewById(R.id.imgElementAnimation);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners() {
        btnReturnToMain.setOnClickListener(this::OnClickListener);

        btnScale.setOnClickListener(this::OnClickListener);
        btnRotate.setOnClickListener(this::OnClickListener);
        btnTransparent.setOnClickListener(this::OnClickListener);
        btnVisibility.setOnClickListener(this::OnClickListener);
        btnCombo.setOnClickListener(this::OnClickListener);
    }

    boolean refreshAnimation = false;

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
                ScaleAnimationModel model = fragmentScale.scaleViewModel.getModel();

                //подготовим анимацию с настройками из модели
                ScaleAnimation scale = new ScaleAnimation(model.getFromXScale() / ScaleAnimationModel.Scalescale, model.getToXScale() / ScaleAnimationModel.Scalescale,
                        model.getFromYScale() / ScaleAnimationModel.Scalescale, model.getToYScale() / ScaleAnimationModel.Scalescale,
                        Animation.RELATIVE_TO_SELF, model.getPivotX() * ScaleAnimationModel.PivotXscale / 100.0f, Animation.RELATIVE_TO_SELF, model.getPivotY() * ScaleAnimationModel.PivotYscale / 100.0f);
                scale.setDuration(model.getDuration());
                scale.setRepeatMode(model.isRepeatMode() ? 2 : 1);
                scale.setRepeatCount(model.getRepeatCount());
                scale.setInterpolator(new LinearInterpolator());

                imgElementAnimation.startAnimation(scale);
            }
            break;
//            case R.id.btnRotate: {
//                FragmentContainerView fr = findViewById(R.id.frRotate);
//                fr.setVisibility(View.VISIBLE);
//
//                imgElementAnimation.clearAnimation();
//
//                // получаем ссылку на фрагмент-приемник
//                RotateFragment fragmentRotate = (RotateFragment) getSupportFragmentManager()
//                        .findFragmentById(R.id.frRotate);
//
//                //получаем модель настроек выбранной анимации
//                assert fragmentRotate != null;
//                RotateAnimationModel model = fragmentRotate.rotateViewModel.getModel();
//
//                //подготовим анимацию с настройками из модели
//                RotateAnimation rotate = new RotateAnimation(model.getFromDegrees(), model.getToDegrees(), Animation.RELATIVE_TO_SELF, model.getPivotX() * RotateAnimationModel.PivotXscale / 100.0f, Animation.RELATIVE_TO_SELF, model.getPivotY() * RotateAnimationModel.PivotYscale / 100.0f);
//                rotate.setDuration(model.getDuration());
//                rotate.setRepeatMode(model.isRepeatMode() ? 2 : 1);
//                rotate.setRepeatCount(model.getRepeatCount());
//                rotate.setInterpolator(new LinearInterpolator());
//
//                imgElementAnimation.startAnimation(rotate);
//            }
//            break;
            case R.id.btnRotate: {
                FragmentContainerView fr = findViewById(R.id.frRotate);
                fr.setVisibility(View.VISIBLE);

                // получаем ссылку на фрагмент-приемник
                RotateFragment fragmentRotate = (RotateFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frRotate);

                //получаем модель настроек выбранной анимации
                assert fragmentRotate != null;
                RotateAnimationModel model = fragmentRotate.rotateViewModel.getModel();

                ViewAnimationAdapter.modelsBase = new BaseAnimationModel[]{model};

                viewAnimationViewModel.getModel().setRefreshAnimation(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewAnimationViewModel.getModel().setRefreshAnimation(true);
                    }
                }, 100);
            }
            break;
            case R.id.btnTranslate: {
                FragmentContainerView fr = findViewById(R.id.frTranslate);
                fr.setVisibility(View.VISIBLE);

                imgElementAnimation.clearAnimation();

                // получаем ссылку на фрагмент-приемник
                TranslateFragment fragmentTranslate = (TranslateFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frTranslate);

                //получаем модель настроек выбранной анимации
                assert fragmentTranslate != null;
                TranslateAnimationModel model = fragmentTranslate.translateViewModel.getModel();

                //подготовим анимацию с настройками из модели
                TranslateAnimation translate = new TranslateAnimation(model.getFromXDelta(), model.getToXDelta(), model.getFromYDelta(), model.getToYDelta());
                translate.setDuration(model.getDuration());
                translate.setRepeatMode(model.isRepeatMode() ? 2 : 1);
                translate.setRepeatCount(model.getRepeatCount());
                translate.setInterpolator(new LinearInterpolator());

                imgElementAnimation.startAnimation(translate);
            }
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
                ComboAnimationModel model = fragmentCombo.comboViewModel.getModel();

                RotateAnimationModel modelRotate = model.getRotateAnimation();

                //подготовим анимацию с настройками из модели
                RotateAnimation rotate = new RotateAnimation(modelRotate.getFromDegrees(), modelRotate.getToDegrees(), Animation.RELATIVE_TO_SELF, modelRotate.getPivotX() * RotateAnimationModel.PivotXscale / 100.0f, Animation.RELATIVE_TO_SELF, modelRotate.getPivotY() * RotateAnimationModel.PivotYscale / 100.0f);
                rotate.setDuration(modelRotate.getDuration());
                rotate.setRepeatMode(modelRotate.isRepeatMode() ? 2 : 1);
                rotate.setRepeatCount(modelRotate.getRepeatCount());
                rotate.setInterpolator(new LinearInterpolator());

                ScaleAnimationModel modelScale = model.getScaleAnimation();

                //подготовим анимацию с настройками из модели
                ScaleAnimation scale = new ScaleAnimation(modelScale.getFromXScale() / ScaleAnimationModel.Scalescale, modelScale.getToXScale() / ScaleAnimationModel.Scalescale,
                        modelScale.getFromYScale() / ScaleAnimationModel.Scalescale, modelScale.getToYScale() / ScaleAnimationModel.Scalescale,
                        Animation.RELATIVE_TO_SELF, modelScale.getPivotX() * ScaleAnimationModel.PivotXscale / 100.0f, Animation.RELATIVE_TO_SELF, modelScale.getPivotY() * ScaleAnimationModel.PivotYscale / 100.0f);
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