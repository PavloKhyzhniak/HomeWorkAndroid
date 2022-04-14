package com.example.homeworkandroid.homework007.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.fragments.RotateFragment;
import com.example.homeworkandroid.homework007.viewmodel.RotateViewModel;

public class Exercises003 extends AppCompatActivity {
    RotateFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework007_activity_exercises003);

        FragmentContainerView fr = findViewById(R.id.frRotate);
        // получаем ссылку на фрагмент-приемник
        fragment = (RotateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frRotate);

//        fr.post(new Runnable() {
//            @Override
//            public void run() {
//                RotateViewModel.RotateViewModelObserver rotateViewModelObserver = fragment.rotateViewModel.getObserver();
//                synchronized(rotateViewModelObserver){
//                    rotateViewModelObserver.notifyAll();
//                    rotateViewModelObserver.setDuration(300);
//                }
//            }
//        });
    }
}